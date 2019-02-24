import java.util.HashMap;

/**
 * 一个节点包括域名、IP地址和存储数据
 *
 * @author Marticles
 * @description Node
 * @date 2019/2/24
 */
public class Node {

    private String domain;

    private String ip;

    private HashMap<String, String> data;

    public Node(String domain, String ip) {
        this.domain = domain;
        this.ip = ip;
        this.data = new HashMap<String, String>(16, 0.75f);
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public HashMap<String, String> getData() {
        return data;
    }

    public void setData(HashMap<String, String> data) {
        this.data = data;
    }

    public void putFile(String file){
        this.data.put(file,file);
    }
}
