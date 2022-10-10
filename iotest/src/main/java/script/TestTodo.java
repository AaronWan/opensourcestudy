package script;

import com.google.common.base.Splitter;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author 万松(Aaron)
 * @creat_date: 2021/6/7
 * @creat_time: 16:24
 * @since 7.5.0
 */
public class TestTodo {
  @SneakyThrows
  public static void main(String[] args) {
    BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(TestQ.class.getResource("/back.log").getPath())));
    Gson gson = new GsonBuilder().create();
    List<Record> rst = gson.fromJson(IOUtils.toString(br), new TypeToken<List<Record>>(){}.getType());
    Map<String, List<Record>> methodGroup = rst.stream().collect(Collectors.groupingBy(item -> item.getRequest().getMethod()));
    methodGroup.forEach((group,items)->{
      System.out.println(group+" \t " + items.size());
    });
  }


  @Data
  class Record{
    Detail fields;
    public RequestBean getRequest(){
      return fields.getDetail();
    }
  }
  @Data
  class Detail{
    List<String> msg;
    public RequestBean getDetail(){
      String request=msg.get(0);
      List<String>details=Splitter.on(",").splitToList(request);
      RequestBean temp = new RequestBean();
      temp.setTime(details.get(3).split(":|ms")[1].trim());
      String[] temps = details.get(5).split(" ");
      temp.setUrl(details.get(5));
      temp.setMethod(temps[3]);
      return temp;
    }
  }
  @Data
  class RequestBean{
    String time;
    String url;
    String method;
  }
}
