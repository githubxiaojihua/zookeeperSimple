import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * 帮助类
 * 用于链接和断开链接到zookeeper集群
 */
public class ZookeeperConnection {

   //声明zookeeper实例，用于链接zookeeper集群
   private ZooKeeper zoo;
   final CountDownLatch countDownLatch = new CountDownLatch(1);

    /**
     * 链接zookeeper集群
     * @param host
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
   public ZooKeeper connect(String host) throws IOException, InterruptedException {
       zoo = new ZooKeeper(host, 5000, new Watcher() {
           public void process(WatchedEvent event) {
               if(event.getState() == Event.KeeperState.SyncConnected){
                    countDownLatch.countDown();
               }
           }
       });

       countDownLatch.await();
       return zoo;
   }

    /**
     * 关闭链接
     * @throws InterruptedException
     */
   public void close() throws InterruptedException {
       zoo.close();
   }

}
