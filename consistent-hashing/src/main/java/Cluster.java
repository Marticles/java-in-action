import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Cluster是对哈希环的抽象
 * 包含了多个Node
 * 这里采用了TreeMap作为存储结构
 *
 * @author Marticles
 * @description Cluster
 * @date 2019/2/24
 */
public abstract class Cluster {

    public SortedMap<Integer, Node> nodes;

    public Cluster() {
        this.nodes = new TreeMap<Integer, Node>();
    }

    public abstract void addNode(Node node);

    public abstract void removeNode(String ip);

    public abstract Node get(String ip);

}