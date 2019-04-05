import java.util.NoSuchElementException;
import java.util.Random;

/**
 * @author Marticles
 * @description SkipList
 * @date 2019/4/6
 */
public class SkipList<K extends Comparable<K>, V>{

    protected static final Random randomGenerator = new Random();

    protected static final double DEFAULT_PROBABILITY = 0.25;

    private Node<K, V> head;

    private double probability;

    private int size;

    public SkipList() {
        this(DEFAULT_PROBABILITY);
    }

    public SkipList(double probability) {
        this.head = new Node<K, V>(null, null, 0);
        this.probability = probability;
        this.size = 0;
    }

    public V get(K key) {
        checkKeyValidity(key);
        Node<K, V> node = findNode(key);
        if (node.getKey().compareTo(key) == 0){
            return node.getValue();
        }
        else{
            return null;
        }
    }

    public void add(K key, V value) {
        // 检查Key是否为空
        checkKeyValidity(key);
        // 若key已存在，则修改对应的value
        Node<K, V> node = findNode(key);
        if (node.getKey() != null && node.getKey().compareTo(key) == 0) {
            node.setValue(value);
            return;
        }
        // 将newNode水平插入到node之后
        Node<K, V> newNode = new Node<K, V>(key, value, node.getLevel());
        horizontalInsert(node, newNode);

        int currentLevel = node.getLevel();
        int headLevel = head.getLevel();
        while (isBuildLevel()) {
            // 如果当前层级已经到达或超越顶层，则需要构建一个新的顶层
            if (currentLevel >= headLevel) {
                Node<K, V> newHead = new Node<K, V>(null, null, headLevel + 1);
                verticalLink(newHead, head);
                head = newHead;
                headLevel = head.getLevel();
            }
            // 找到node对应的上一层节点
            while (node.getUp() == null) {
                node = node.getPrevious();
            }
            node = node.getUp();
            // 将newNode复制到上一层
            Node<K, V> tmp = new Node<K, V>(key, value, node.getLevel());
            horizontalInsert(node, tmp);
            verticalLink(tmp, newNode);
            newNode = tmp;
            currentLevel++;
        }
        size++;
    }

    // 删除需要先找到第1层的节点，再向上查找所有节点
    public void remove(K key) {
        checkKeyValidity(key);
        Node<K, V> node = findNode(key);
        if (node == null || node.getKey().compareTo(key) != 0){
            throw new NoSuchElementException("The key is not exist!");
        }
        // 移动到最底层
        while (node.getDown() != null){
            node = node.getDown();
        }
        // 自底向上地进行删除
        Node<K, V> prev = null;
        Node<K, V> next = null;
        for (; node != null; node = node.getUp()) {
            prev = node.getPrevious();
            next = node.getNext();
            if (prev != null){
                prev.setNext(next);
            }
            if (next != null){
                next.setPrevious(prev);
            }
        }

        // 对顶层链表进行调整，去除无效的顶层链表
        while (head.getNext() == null && head.getDown() != null) {
            head = head.getDown();
            head.setUp(null);
        }
        size--;
    }

    public boolean contains(K key) {
        return get(key) != null;
    }

    public int size() {
        return size;
    }

    public boolean empty() {
        return size == 0;
    }

    protected Node<K, V> findNode(K key) {
        Node<K, V> node = head;
        Node<K, V> next = null;
        Node<K, V> down = null;
        K nodeKey = null;

        while (true) {
            // 遍历直到遇见大于目标元素的节点
            next = node.getNext();
            while (next != null && lessThanOrEqual(next.getKey(), key)) {
                node = next;
                next = node.getNext();
            }
            // 当前元素等于目标元素，结束查找
            nodeKey = node.getKey();
            if (nodeKey != null && nodeKey.compareTo(key) == 0){
                break;
            }
            // 否则，跳跃到下一层级
            down = node.getDown();
            if (down != null) {
                node = down;
            } else {
                break;
            }
        }
        return node;
    }

    // 对key进行检查
    // 因为每条链表的头节点就是一个key为null的节点，所以不允许其他节点的key也为null
    protected void checkKeyValidity(K key) {
        if (key == null){
            throw new IllegalArgumentException("Key must be not null!");
        }
    }

    // a是否小于等于b
    protected boolean lessThanOrEqual(K a, K b) {
        return a.compareTo(b) <= 0;
    }

    // 概率函数
    protected boolean isBuildLevel() {
        return randomGenerator.nextDouble() < probability;
    }

    // 将y水平插入至x后
    protected void horizontalInsert(Node<K, V> x, Node<K, V> y) {
        y.setPrevious(x);
        y.setNext(x.getNext());
        if (x.getNext() != null){
            x.getNext().setPrevious(y);
        }
        x.setNext(y);
    }

    // 垂直连接x与y
    protected void verticalLink(Node<K, V> x, Node<K, V> y) {
        x.setDown(y);
        y.setUp(x);
    }

    protected static class Node<K extends Comparable<K>, V> {

        private K key;

        private V value;

        private int level;

        private Node<K, V> up, down, next, previous;

        public Node(K key, V value, int level) {
            this.key = key;
            this.value = value;
            this.level = level;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public Node<K, V> getUp() {
            return up;
        }

        public void setUp(Node<K, V> up) {
            this.up = up;
        }

        public Node<K, V> getDown() {
            return down;
        }

        public void setDown(Node<K, V> down) {
            this.down = down;
        }

        public Node<K, V> getNext() {
            return next;
        }

        public void setNext(Node<K, V> next) {
            this.next = next;
        }

        public Node<K, V> getPrevious() {
            return previous;
        }

        public void setPrevious(Node<K, V> previous) {
            this.previous = previous;
        }
    }

}
