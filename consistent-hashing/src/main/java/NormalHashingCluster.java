/**
 * @author Marticles
 * @description NormalHashingCluster
 * @date 2019/2/24
 */
public class NormalHashingCluster extends Cluster {

    public NormalHashingCluster() {
        super();
    }

    @Override
    public void addNode(Node node) {
        this.nodes.put(node.getIp().hashCode() % (nodes.size() + 1), node);
    }

    @Override
    public void removeNode(String ip) {
        this.nodes.remove(ip.hashCode() % (nodes.size() + 1));
    }

    @Override
    public Node get(String ip) {
        return this.nodes.get(ip.hashCode() % (nodes.size() + 1));
    }

}
