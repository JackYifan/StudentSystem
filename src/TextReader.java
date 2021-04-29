import java.io.*;

/**
 * @Author Yifan Wu
 * Date on 2021/4/14  19:18
 */
public class TextReader {
    String fileName;

    public TextReader(String fileName) {
        this.fileName = fileName;
    }

    /**
     *
     * @return 文本中的信息
     */
    public String getText(){
        String str = "";
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(this.fileName),"UTF-8"));
            String buf;
            while((buf=in.readLine())!=null){
                str += buf+"\n";
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
}
