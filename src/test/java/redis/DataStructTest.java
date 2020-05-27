package redis;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.JedisCluster;

import java.util.List;
import java.util.Set;

/**
 * 测试redis核心数据结构
 */
public class DataStructTest {

    JedisCluster redis;

    @Before
    public void before(){
        redis = RedisCluster.getInstance();
    }

    @Test
    public void testString(){
        String k = "foo";
        String v = "bar";
        String v2 = "goo";
        Assert.assertNull(redis.get(k));
        Assert.assertFalse(redis.exists(k));
        redis.setex(k,2,v);
        Assert.assertTrue(redis.ttl(k)>0);
        Assert.assertEquals(v, redis.get(k));

        //字符串append
        redis.append(k,v2);
        Assert.assertEquals(v+v2, redis.get(k));

        //字符串截取
        Assert.assertEquals(v, redis.getrange(k,0,2));
    }

    //不支持集群模式
    @Test
    public void testBatch(){
        String k1 = "k1";
        String k2 = "k3";
        String k3 = "k2";
        String v1 = "foo";
        String v2 = "bar";
        String v3 = "goo";
        redis.mset(k1,v1,k2,v2,k3,v3);
        final List<String> r = redis.mget(k1, k2, k3);
        Assert.assertEquals(3, r.size());
        Assert.assertEquals(v1, r.get(0));
        Assert.assertEquals(v2, r.get(1));
        Assert.assertEquals(v3, r.get(2));
    }

    @Test
    public void testList(){
        String k = "k";
        String v1 = "foo";
        String v2 = "bar";
        String v3 = "goo";
        redis.del(k);
        redis.lpush(k,v1);
        //插入队头
        redis.lpush(k,v2);
        //插入队尾
        redis.rpush(k,v3);
        Assert.assertEquals(3L, redis.llen(k).longValue());
        List<String> r = redis.lrange(k, 0, -1);
        Assert.assertEquals(v2, r.get(0));
        Assert.assertEquals(v1, r.get(1));
        Assert.assertEquals(v3, r.get(2));

        Assert.assertEquals(v1, redis.lindex(k,1));

        Assert.assertEquals(v2, redis.lpop(k));
        Assert.assertEquals(v3, redis.rpop(k));
        Assert.assertEquals(v1, redis.rpop(k));

        redis.lpush(k,v1);
        //插入队头
        redis.lpush(k,v2);
        //插入队尾
        redis.rpush(k,v3);

        redis.ltrim(k, 0,1);

        r = redis.lrange(k, 0, -1);

        Assert.assertEquals(2, r.size());
        Assert.assertEquals(v2, r.get(0));
        Assert.assertEquals(v1, r.get(1));

        //删除指定index的元素
        //标记指定index元素
        redis.lset(k, 1,"del");
        //删除所有=标记的元素
        redis.lrem(k, 0, "del");
        r = redis.lrange(k, 0, -1);
        Assert.assertEquals(1, r.size());
        Assert.assertEquals(v2, r.get(0));
    }

    @Test
    public void testHashMap(){
        String k = "hash";
        String hk1 = "key";
        String hk2 = "count";
        String hv1 = "tag";
        String hv2 = "0";
        redis.del(k);

        redis.hset(k, hk1, hv1);
        redis.hset(k, hk2, hv2);

        redis.hincrBy(k, hk2,5);
        Assert.assertEquals(5, Integer.parseInt(redis.hget(k, hk2)));

        redis.hdel(k, hk1);
        Assert.assertNull(redis.hget(k, hk1));
    }


    @Test
    public void testSet(){
        String k = "k1";
        String v1 = "v1";
        String v2 = "v2";
        String v3 = "v3";
        redis.del(k);
        redis.sadd(k, v1);
        redis.sadd(k, v2);
        redis.sadd(k, v3);
        Set<String> smembers = redis.smembers(k);

        Assert.assertEquals(3, redis.scard(k).intValue());
        Assert.assertTrue(smembers.contains(v1));
        Assert.assertTrue(smembers.contains(v2));
        Assert.assertTrue(smembers.contains(v3));

        String k2 = "k2";
        String v4 = "v4";
        String v5 = "v5";
        String v6 = "v6";
        redis.sadd(k2, v1);
        redis.sadd(k2, v2);
        redis.sadd(k2, v3);
        redis.sadd(k2, v4);
        redis.sadd(k2, v5);
        redis.sadd(k2, v6);
        smembers = redis.smembers(k2);
        Assert.assertEquals(6, smembers.size());

        //集群模式不能使用
   /*     Assert.assertEquals(3, redis.sinter(k,k2).size());
        Assert.assertEquals(6, redis.sunion(k,k2).size());
        Assert.assertEquals(3, redis.sdiff(k,k2).size());*/
    }

    @Test
    public void testSortedSet(){
        String k = "k1";
        String v1 = "v1";
        String v2 = "v2";
        String v3 = "v3";
        String v4 = "v4";
        String v5 = "v5";
        redis.del(k);
        redis.zadd(k,2.0, v2);
        redis.zadd(k, 1.0,v1);
        redis.zadd(k, 1.0,v4);
        redis.zadd(k, 1.0,v5);
        redis.zadd(k,3.0, v3);

        Assert.assertEquals(5, redis.zcard(k).intValue());
        Assert.assertEquals(3, redis.zcount(k,0,1).intValue());

        Set<String> r = redis.zrange(k, 0, -1);
        System.out.println(r);
    }
}
