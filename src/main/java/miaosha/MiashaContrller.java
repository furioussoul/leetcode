package miaosha;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.RedisCluster;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RestController
@RequestMapping("/miaosha")
public class MiashaContrller {

    @Resource
    GOODSMapper goodsMapper;

    volatile boolean soldOut = true;
    AtomicInteger count = new AtomicInteger();

    @GetMapping(value = "/view")
    public GoodsDO view() {
        return goodsMapper.selectByExample(new GoodsDOExample()).get(0);
    }

    @GetMapping(value = "/refresh")
    public int refresh(int stock) {
        log.info("last count :{}", count.get());
        count.set(0);
        soldOut = false;
        final JedisCluster redis = RedisCluster.getInstance();
        redis.set("goods", "" + stock);
        redis.expire("goods", 60);
        final long c = goodsMapper.countByExample(new GoodsDOExample());
        if (c > 0) {
            final GoodsDOExample example = new GoodsDOExample();
            example.createCriteria().andGoodIdEqualTo(1);
            goodsMapper.deleteByExample(example);
        }
        final GoodsDO goodsDO = new GoodsDO();
        goodsDO.setGoodId(1);
        goodsDO.setLeftCount(stock);
        goodsDO.setName("god");
        return goodsMapper.insertSelective(goodsDO);
    }

    @GetMapping(value = "/miaosha_nocache")
    public Object miaosha_nocache(String account) {
        return testNoCache(account);
    }


    /**
     * 售完、该用户已经下过单则返回失败。
     * 锁该用户，若redis库存大于0，则减1。若发生异常且stock！=null则加1。
     * 下单，若失败就查询订单信息，若没有订单信息则库存加一
     *
     * @param account
     * @return
     */
    @GetMapping(value = "/miaosha")
    public Object miaosha(String account) {
        Long stock = null;
        final JedisCluster redis = RedisCluster.getInstance();
        try {

            if (soldOut) {
                log.info("已售完");
                return 0;
            }

            //是否已下单
            final String order = redis.get(getMiaoShaOrderKey(account));
            if (order != null) {
                log.info("已参与，不能继续秒杀");
                return 0;
            }

            //设置排队中
            if (null == redis.set(getMiaoShaWaitKey(account), "", "nx", "ex", 10)) {
                log.info("在队列中");
                return 0;
            }

            stock = redis.decr("goods");
            log.info("stock:{}", stock);
            if (stock == null) {
                log.info("还未开始秒杀");
                return 0;
            }

            if (stock < 0) {
                redis.incr("goods");
                soldOut = true;
                log.info("已售完");
                return 0;
            }
        } catch (Exception ex) {
            if (stock != null) {
                redis.incr("goods");
            }
            soldOut = false;
            log.error(ex.getMessage(), ex);
            return 0;
        }

        try {
            //创建订单信息
            final String set = redis.set(getMiaoShaOrderKey(account), "order", "nx", "ex", 10);
            if (set == null) {
                log.info("创建订单失败");
                throw new RuntimeException("创建订单失败");
            }
            count.incrementAndGet();
            //扣库存
            final int i = goodsMapper.decCount(1);
            if (i == 0) {
                log.info("扣减库存失败");
                throw new RuntimeException("扣减库存失败");
            } else {
                log.info("扣减库存成功");
            }
        } catch (Exception ex) {
            if (null != redis.get(getMiaoShaOrderKey(account))) {
                redis.incr("goods");
                soldOut = false;
            }

            log.error(ex.getMessage(), ex);
        } finally {
            redis.del(getMiaoShaWaitKey(account));
        }

        return 1;
    }

    private int testNoCache(String account) {
        final JedisCluster redis = RedisCluster.getInstance();
        //创建订单信息
        final String set = redis.set(getMiaoShaOrderKey(account), "order", "nx", "ex", 10);
        if (set == null) {
            log.info("创建订单失败");
            throw new RuntimeException("创建订单失败");
        }

        final int i = goodsMapper.decCount(1);
        if (i == 0) {
            log.info("扣减库存失败");
        } else {
            log.info("扣减库存成功");
        }

        return i;
    }

    @GetMapping(value = "/order")
    public int order(String account) {
        final JedisCluster redis = RedisCluster.getInstance();
        final String s = redis.get(getMiaoShaOrderKey(account));
        return "order".equals(s) ? 1 : 0;
    }


    public String getMiaoShaWaitKey(String account) {
        return "miaosha:wait:" + account;
    }

    public String getMiaoShaVerifyKey(String account) {
        return "miaosha:verify:" + account;
    }

    public String getMiaoShaOrderKey(String account) {
        return "miaosha:order:" + account;
    }

}
