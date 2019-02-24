import java.util.List;

/**
 * @author Marticles
 * @description NormalHashingClusterTest
 * @date 2019/2/24
 */
public class NormalHashingClusterTest {

    public static void main(String[] args) {
        NormalHashingCluster cluster = buildCluster();
        // testSearchNode(cluster, TestFiles.getTestFiles());
        // testDeleteNode(cluster);
        testAddNode(cluster);
    }

    private static NormalHashingCluster buildCluster() {
        NormalHashingCluster cluster = new NormalHashingCluster();
        cluster.addNode(new Node("www.test1.com", "192.168.0.1"));
        cluster.addNode(new Node("www.test2.com", "192.168.0.2"));
        cluster.addNode(new Node("www.test3.com", "192.168.0.3"));
        cluster.addNode(new Node("www.test4.com", "192.168.0.4"));
        System.out.println("*****************节点新建完成*****************");
        for (String file : TestFiles.getTestFiles()) {
            Node node = cluster.get("192.168.0." + file.hashCode() % cluster.nodes.size());
            node.putFile(file);
        }
        System.out.println("*****************文件插入完成*****************");
        return cluster;

    }

    private static void testSearchNode(NormalHashingCluster cluster, List<String> fileList) {
        System.out.println("*****************开始查找*****************");
        int searchFailCounter = 0;
        for (String file : fileList) {
            Node node = cluster.get("192.168.0." + file.hashCode() % cluster.nodes.size());
            if (null == node.getData().get(file) || !file.equals(node.getData().get(file))) {
                searchFailCounter++;
            }else{
                System.out.println(node.getDomain() + "  " + node.getIp() + "  " + node.getData().get(file));
            }
        }

        System.out.println("*****************查找完成*****************");
        float hitRate = searchFailCounter != fileList.size() ? 1 - (searchFailCounter / (float) fileList.size()) : 0;
        System.out.println("查找失败数：" + searchFailCounter);
        System.out.println("命中率：" + hitRate);
    }

    private static void testDeleteNode(NormalHashingCluster cluster) {
        cluster.removeNode("192.168.0.4");
        testSearchNode(cluster, TestFiles.getTestFiles());
    }

    private static void testAddNode(NormalHashingCluster cluster) {
        cluster.addNode(new Node("www.test5.com", "192.168.0.5"));
        testSearchNode(cluster, TestFiles.getTestFiles());
    }

}
