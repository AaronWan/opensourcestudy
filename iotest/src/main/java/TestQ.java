import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.Map;

/**
 * @author 万松(Aaron)
 * @creat_date: 2019-09-19
 * @creat_time: 21:26
 * @since 6.6
 */
public class TestQ {
  public static void main(String[] args) throws IOException {
    BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(TestQ.class.getResource("/data.log").getPath())));
    String line=null;
    while ((line=br.readLine())!=null){
      String[] data = line.split(",");
      for (int i = 0; i < data.length; i++) {
        System.out.println("curl -X POST \\\n" + "  http://172.17.4.230:50000/fs-flow-biz/flow/def/updateFunctionBind \\\n" +
          "  -H 'cache-control: no-cache' \\\n" + "  -H 'content-type: application/json' \\\n" +
          "  -H 'postman-token: ff5ea1c7-ecb8-bbac-63ec-96f08a7d6427' \\\n" + "  -H 'x-tenant-id: "+data[i]+"' \\\n" + "  -H 'x-user-id: 1000' \\\n" + "  -d '{}'");
      }

    }
  }
}
