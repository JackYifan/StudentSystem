import java.io.*;

/**
 * @Author Yifan Wu
 * Date on 2021/4/14  19:32
 */
public class TextWriter {
    String fileName ;

    public TextWriter(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void write(String str){
        try {
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(new File(fileName), true),
                            "UTF-8"
                    )
            );
            writer.write(str+"\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
