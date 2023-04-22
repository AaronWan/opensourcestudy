package script;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 万松(Aaron)
 * @creat_date: 2019-09-19
 * @creat_time: 21:26
 * @since 6.6
 */
public class TestData {
  public static final Gson gson = new GsonBuilder().create();
  public static void main(String[] args) throws IOException {
    BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(TestData.class.getResource("/data.log").getPath())));
    String line=null;
    Map<String, Object> result = Maps.newHashMap();
    while ((line=br.readLine())!=null){
      Map<String, List<String>> data = gson.fromJson(line, Map.class);
      data.forEach((key,values)-> {
        result.put(key, values.stream().filter(item -> !item.contains("fktest")).collect(Collectors.toSet()));
      });
    }
    System.out.println(gson.toJson(result));
  }
}
