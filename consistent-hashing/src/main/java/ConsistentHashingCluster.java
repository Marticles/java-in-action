/**
 * @author Marticles
 * @description ConsistentHashingCluster
 * @date 2019/2/24
 */
public class ConsistentHashingCluster extends Cluster {

    // 简单起见这里没用2^32
    private Integer size = 1024;
    // 每个节点有32个虚拟节点
    private Integer virtualNodesNum = 32;

    public ConsistentHashingCluster() {
        super();
    }

    @Override
    public void addNode(Node node) {
        this.nodes.put(node.getIp().hashCode() % size, node);
        for (int i = 0; i < virtualNodesNum; i++) {
            Node newNode = new Node(node.getDomain() + "#vir" + i, node.getIp() + "#vir" + i);
            this.nodes.put(Math.abs((node.getIp() + i).hashCode()) % size, newNode);
        }
    }

    @Override
    public void removeNode(String ip) {
        Node node = this.nodes.get(ip.hashCode() % size);
        this.nodes.remove(ip.hashCode() % size);
        for (int i = 0; i < virtualNodesNum; i++) {
            this.nodes.remove(Math.abs((node.getIp() + i).hashCode()) % size);
        }
    }

    @Override
    public Node get(String ip) {
        return this.nodes.get(ip.hashCode() % size);
    }

    public void putFile(String file) {
        if (null == this.nodes.get(file.hashCode() % size)) {
            Node firstNode = this.nodes.get(nodes.firstKey());
            firstNode.putFile(file);
        } else {
            this.nodes.get(file.hashCode() % size).putFile(file);
        }
    }

    public Node getFileNode(String file) {
        if (null == this.nodes.get(file.hashCode() % size)) {
            return this.nodes.get(nodes.firstKey());
        } else {
            Node tempNode = this.nodes.get(file.hashCode() % size);
            String ip = tempNode.getIp();
            // 如果是虚拟节点
            if (ip.contains("#")) {
                // 找到对应的真实节点
                int i = ip.indexOf("#");
                String realIp = ip.substring(0, i);
                return this.nodes.get(realIp.hashCode() % size);
            } else {
                return this.nodes.get(ip.hashCode() % size);
            }
        }
    }
}
