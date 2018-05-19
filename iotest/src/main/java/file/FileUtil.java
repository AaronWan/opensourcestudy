package file;

import org.junit.Test;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

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
}
