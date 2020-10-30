package file;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.Data;

import java.io.*;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author 万松(Aaron)
 * @creat_date: 2019-10-28
 * @creat_time: 21:41
 * @since 6.6
 */
public class FixDeptTree {
  public static void main(String[] args) throws FileNotFoundException {
    List<Dept> rst = Lists.newArrayList();
    getPath("1137", getTree(), rst, 1);
    System.out.println(Joiner.on("->").join(rst.stream().map(item -> item.getName() + "(" + item.getId() + ")").toArray()));
  }

  public static boolean getPath(String deptId, List<Dept> depts, List<Dept> rst, int isDept) {
    for (int i = 0; i < depts.size(); i++) {
      Dept dept = depts.get(i);
      if (dept.getId().equals(deptId) && dept.isDept == isDept) {
        rst.add(dept);
        return true;
      } else if (dept.isDept == 1) {
        if (getPath(deptId, dept.getChildren(), rst, isDept)) {
          rst.add(dept);
          return true;
        }
      }
    }
    return false;
  }

  private static Gson gson = new GsonBuilder().create();

  private static List<Dept> getTree() throws FileNotFoundException {
    StringBuffer sb = new StringBuffer();
    readFileByLine(new FileInputStream(FixDeptTree.class.getResource("/dept.txt").getPath()), s -> sb.append(s));
    TypeToken<List<Dept>> type = new TypeToken<List<Dept>>() {

    };
    return gson.fromJson(sb.toString(), type.getType());
  }

  private static void readFileByLine(FileInputStream fis, Consumer<String> consumer) {
    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
    String line;
    try {
      while ((line = br.readLine()) != null) {
        consumer.accept(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Data
  static class Dept {
    Integer isDept;
    String id;
    String name;
    List<Dept> children;
  }
}
