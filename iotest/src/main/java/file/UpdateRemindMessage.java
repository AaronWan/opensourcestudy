package file;

import file.flow.SlowTest;
import lombok.Builder;
import lombok.Data;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Map;

import static file.FileUtil.readFileByLine;

/**
 * @author 万松(Aaron)
 * @creat_date: 2019-09-11
 * @creat_time: 22:43
 * @since 6.6
 */
public class UpdateRemindMessage
{
  @Data
  @Builder
  static class PostRequest {
    String url;
    String body;
    Map<String, String> headers;

    @Override
    public String toString() {
      StringBuffer head = new StringBuffer();
      headers.forEach((key, value) -> {
        head.append("  -H '" + key + ": " + value + "' \\\n");
      });
      String rst = "curl -X POST \\\n" + "  '" + url.trim() + "' \\\n" + head.toString() + "  -d '" + body + "' \n";
      return rst;
    }
  }

  @Test
  public void update_message() throws Exception {
    FileInputStream fis = new FileInputStream(SlowTest.class.getResource("/error_data.log").getPath());
    PrintWriter pw = new PrintWriter(new FileOutputStream(SlowTest.class.getResource("/update_message.log").getPath(), false));
    readFileByLine(fis, (line) -> {
      String[] data = line.split(",");
      String tenantId=data[0];
      String remindId=data[1];
      pw.println("tdb.QixinScheduleMessage.update_one({'tenantId':'"+tenantId+"','remindId':'"+remindId+"'},{\"$set\":{'delFlag':True}});");
    });
    pw.close();
  }


}
