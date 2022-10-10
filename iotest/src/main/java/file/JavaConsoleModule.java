package file;

import com.google.common.base.Splitter;
import file.flow.SlowTest;
import org.junit.Test;

import java.io.FileInputStream;
import java.util.List;

import static file.FileUtil.readFileByLine;

/**
 * @author 万松(Aaron)
 * @creat_date: 2019-09-11
 * @creat_time: 22:43
 * @since 6.6
 */
public class JavaConsoleModule
{

  @Test
  public void update_message() throws Exception {

    FileInputStream fis = new FileInputStream(SlowTest.class.getResource("/module.log").getPath());
    readFileByLine(fis, (line) -> {
      List<String> temps=Splitter.on(" ").splitToList(line);
      String rights=temps.get(0);
      String first=temps.get(2);
      String name=temps.get(3);
      System.out.println("AppModule.of(\""+name+"\",\""+first+"\",\""+rights+"\"),");
    });
  }


}
