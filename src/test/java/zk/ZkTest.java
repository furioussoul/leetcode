package zk;

import com.alibaba.fastjson.JSON;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

public class ZkTest {

    @Test
    public void testNode() throws KeeperException, InterruptedException {
        String p = "/foo";
        String v= "bar";
        final ZooKeeper zk = new ZKClient().zooKeeper;
        zk.create(p, v.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        Stat stat = new Stat();
        zk.getData(p, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("-------watch2: ");
            }
        }, stat);
        System.out.println("-------Stat: " + JSON.toJSONString(stat));
        zk.setData(p, v.getBytes(), stat.getVersion());
        zk.delete(p,stat.getVersion()+1);
    }

    @Test
    public void watch() throws KeeperException, InterruptedException {
        final ZKClient zkClient = new ZKClient();
        final ZooKeeper zk = zkClient.zooKeeper;
        String znode = "/foo";
        if(zk.exists(znode, false) == null){
            final String s = zk.create(znode, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }

        final String s = zk.create(znode + "/abc", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("--------------" + s);
        Stat stat = new Stat();
        try {
            zk.getData(znode,zkClient, stat);
        } catch (InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
