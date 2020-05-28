package zk;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.List;

public class ZKClient implements Watcher, AsyncCallback.ChildrenCallback {
    public ZooKeeper zooKeeper;
    private static String connectString = "47.114.167.224:2181,47.114.167.224:2182,47.114.167.224:2183";

    public ZKClient() {
        try {
            zooKeeper = new ZooKeeper(connectString, 1000, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void processResult(int rc, String path, Object ctx, List<String> children) {
        System.out.println(rc);
        System.out.println(path);
        System.out.println(ctx);
        System.out.println(children);
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("----event----");
        System.out.println(event);
        try {
            if(event.getPath() != null){
                zooKeeper.exists(event.getPath(), true);
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
