import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import file.flow.SlowTest;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author 万松(Aaron)
 * @creat_date: 2019-09-19
 * @creat_time: 21:26
 * @since 6.6
 */
public class TestQ {
  public static void main(String[] args) throws IOException {
    BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(SlowTest.class.getResource("/back.log").getPath())));
    Gson gson = new GsonBuilder().create();
    ArrayList rst = gson.fromJson(br.readLine(), ArrayList.class);
    rst.forEach(item-> System.out.println(((Map)item).get("id")));
  }
}
