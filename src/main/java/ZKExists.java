import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

public class ZKExists {
    private static ZookeeperConnection conn;
    private static ZooKeeper zk;

    /**
     * 查询Znode是否存在
     * @param path
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    public static Stat znode_exists(String path) throws KeeperException, InterruptedException {
        return zk.exists(path, true);
    }

    public static void main(String[] args) {
        String path = "/MyFirstZnode";
        try {
            conn = new ZookeeperConnection();
            zk = conn.connect("192.168.33.130");
            Stat stat = znode_exists(path);
            if(stat != null){
                System.out.println("Node exsits and the node version is " + stat.getVersion());
            }else{
                System.out.println("Node does not exists!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

}
