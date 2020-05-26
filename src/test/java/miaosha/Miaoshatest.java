package miaosha;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Test;
import redis.RedisCluster;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Miaoshatest {

    @Test
    public void miaosha() {

        int[] persons = new int[200];
        for (int i = 0; i < persons.length; i++) {
            persons[i] = i;
        }

        RedisCluster.getInstance().set("goods", "100");
        RedisCluster.getInstance().expire("goods", 60);

        //初始化库存=100
        get("http://localhost:4566/miaosha/refresh");
        AtomicInteger count = new AtomicInteger();
        CountDownLatch latch = new CountDownLatch(200);
        //开启200个线程去抢
        for (int person : persons) {
            final int p = person;
            final Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    String r = get("http://localhost:4566/miaosha/miaosha?account=" + p);
                    if (r.equals("1")) {
                        r = get("http://localhost:4566/miaosha/order?account=" + p);
                        if (r.equals("1")) {
                            count.incrementAndGet();
                        }
                    }
                    latch.countDown();
                }
            });

            thread.setName("thread-" + person);
            thread.start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        final String r = get("http://localhost:4566/miaosha/view");
        final JSONObject jsonObject = JSONObject.parseObject(r);
        Assert.assertEquals(count.get(), 100 - (int) jsonObject.get("leftCount"));
    }


    public String get(String url) {
        final CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Get请求
        HttpGet httpGet = new HttpGet(url);

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                final String r = EntityUtils.toString(responseEntity);
                System.out.println("响应内容为:" + r);
                return r;
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
