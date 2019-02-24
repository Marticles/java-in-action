import java.util.ArrayList;
import java.util.List;

/**
 * @author Marticles
 * @description TestFiles
 * @date 2019/2/24
 */
public class TestFiles {

    public List<String> fileList;

    public static List<String> getTestFiles() {
        List<String> fileList = new ArrayList<String>();
        fileList.add("a.jpg");
        fileList.add("b.jpg");
        fileList.add("c.png");
        fileList.add("d.svg");
        return fileList;
    }
}
