package miaosha;

import com.alibaba.fastjson.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.*;

public class Miaoshatest {
    volatile CountDownLatch latch = new CountDownLatch(1000);
    ThreadPoolExecutor threadPoolExecutor;
    @Before
    public void bef() {
        threadPoolExecutor = new ThreadPoolExecutor(1000, 1000, 60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(100));
    }
    @After
    public void aft() {
        threadPoolExecutor.shutdown();
    }

    @Test
    public void miaosha() {

        //初始化库存=100
        HttpUtil.httpGet("http://localhost:4566/miaosha/refresh?stock=100");

        int[] persons = new int[1000];
        for (int i = 0; i < persons.length; i++) {
            persons[i] = i;
        }

        final int[] p1 = Arrays.copyOfRange(persons, 0, 400);
        final int[] p2 = Arrays.copyOfRange(persons, 400, 500);
        final int[] p3 = Arrays.copyOfRange(persons, 500, 1000);

        base(p1, true);
        base(p2, false);
        base(p3, true);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            //for 请求超时提前返回
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        final String r = HttpUtil.httpGet("http://localhost:4566/miaosha/view");
        final JSONObject jsonObject = JSONObject.parseObject(r);
        Assert.assertEquals(0, (int) jsonObject.get("leftCount"));
    }

    private void base(int[] persons, boolean random) {

        for (int person : persons) {
            final int p = person;
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    int t = p;
                    if (random) {
                        t = new Random().nextInt(1000);
                    }
                    HttpUtil.httpGet("http://localhost:4566/miaosha/miaosha?account=" + t);
                    latch.countDown();
                }
            });
        }

    }
}
