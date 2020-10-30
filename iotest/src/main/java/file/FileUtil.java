package file;

import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.util.function.Consumer;

/**
 * @author 万松(Aaron)
 * @since 6.2
 */
public class FileUtil {
    @Test
    public void write(){
        String test="abc";

        try(FileOutputStream fos=new FileOutputStream("test.txt")){
            fos.write(test.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }

    }

    @Test
    public void writeBin(){
        String test="abc中国";
        Charset.availableCharsets().forEach((key,value)->{
                    System.out.println(key+","+value);
        }
        );
        try(DataOutputStream fos=new DataOutputStream(new FileOutputStream("test1.txt"))){
            fos.writeBytes(test);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }

    }


    public static void readFileByLine(FileInputStream fis, Consumer<String> consumer) {
        BufferedReader br=new BufferedReader(new InputStreamReader(fis));
        String line;
        try {
            while((line=br.readLine())!=null){
                consumer.accept(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
