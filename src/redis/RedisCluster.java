package redis;

import redis.clients.jedis.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 访问redis集群
 */
public class RedisCluster {
    public static void main(String[] args) throws IOException {
        cluster();
    }

    public static void oneNode(){
        JedisShardInfo shardInfo = new JedisShardInfo("redis://127.0.0.1:8006");//这里是连接的本地地址和端口
        shardInfo.setPassword("redispwd");
        Jedis jedis = new Jedis(shardInfo);
        jedis.connect();
        System.out.println(jedis.get("a"));
        System.out.println(jedis.set("a","1"));
        System.out.println(jedis.get("a"));
    }

    public static void cluster() throws IOException {
        Set<HostAndPort> jedisClusterNode = new HashSet<HostAndPort>();
        jedisClusterNode.add(new HostAndPort("47.114.167.224", 8001));
        jedisClusterNode.add(new HostAndPort("47.114.167.224", 8002));
        jedisClusterNode.add(new HostAndPort("47.114.167.224", 8003));
        jedisClusterNode.add(new HostAndPort("47.114.167.224", 8004));
        jedisClusterNode.add(new HostAndPort("47.114.167.224", 8005));
        jedisClusterNode.add(new HostAndPort("47.114.167.224", 8006));

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        config.setMaxIdle(10);
        config.setTestOnBorrow(true);
        //connectionTimeout：指的是连接一个url的连接等待时间
        //soTimeout：指的是连接上一个url，获取response的返回等待时间
        JedisCluster jedisCluster = new JedisCluster(jedisClusterNode, 6000, 5000, 10, "redispwd", config);
        System.out.println(jedisCluster.set("student", "zhuge"));
        System.out.println(jedisCluster.set("age", "19"));

        System.out.println(jedisCluster.get("student"));
        System.out.println(jedisCluster.get("age"));

        jedisCluster.close();
    }
}
