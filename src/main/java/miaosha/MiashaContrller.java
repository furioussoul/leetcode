package miaosha;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.RedisCluster;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/miaosha")
public class MiashaContrller {

    @Resource
    GOODSMapper goodsMapper;

    Map<String, Boolean> soldOut = new HashMap<>();

    @GetMapping(value = "/view")
    public GoodsDO view() {
        return goodsMapper.selectByExample(new GoodsDOExample()).get(0);
    }

    @GetMapping(value = "/refresh")
    public int refresh() {
        soldOut.put("1", false);
        final long c = goodsMapper.countByExample(new GoodsDOExample());
        if (c > 0) {
            final GoodsDOExample example = new GoodsDOExample();
            example.createCriteria().andGoodIdEqualTo(1);
            goodsMapper.deleteByExample(example);
        }
        final GoodsDO goodsDO = new GoodsDO();
        goodsDO.setGoodId(1);
        goodsDO.setLeftCount(100);
        goodsDO.setName("god");
        return goodsMapper.insertSelective(goodsDO);
    }

    @GetMapping(value = "/miaosha")
    public Object miaosha(String account) {
        final JedisCluster redis = RedisCluster.getInstance();
        try {

            if (soldOut.get("1")) {
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
            if (null == redis.set(getMiaoShaWaitKey(account), "", "nx", "ex", 60)) {
                log.info("排队失败");
                return 0;
            }

            final Long stock = redis.decr("goods");
            log.info("stock:{}", stock);
            if (stock == null) {
                log.info("还未开始秒杀");
                return 0;
            }

            if (stock < 0) {
                redis.incr("goods");
                soldOut.put("1",true);
                log.info("已售完");
                return 0;
            }
        } catch (Exception ex) {

            log.error(ex.getMessage(), ex);
            return 0;
        }

        try {
            //创建订单信息
            final String set = redis.set(getMiaoShaOrderKey(account), "order", "nx", "ex", 60);
            if (set == null) {
                log.info("创建订单失败");
                throw new RuntimeException("创建订单失败");
            }

            //扣库存
            final int i = goodsMapper.decCount(1);
            if (i == 0) {
                log.info("扣减库存失败");
            } else {
                log.info("扣减库存成功");
            }
        } catch (Exception ex){
            log.error(ex.getMessage(), ex);
            redis.incr("goods");
            soldOut.put("1", false);
        } finally {
            redis.del(getMiaoShaWaitKey(account));
        }

        return 1;
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
