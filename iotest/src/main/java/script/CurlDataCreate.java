package script;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import file.flow.SlowTest;
import lombok.Builder;
import lombok.Data;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static file.FileUtil.readFileByLine;

/**
 * @author 万松(Aaron)
 * @creat_date: 2019-07-07
 * @creat_time: 17:21
 * @since 6.6
 */
public class CurlDataCreate {
  @Data
  @Builder
  static class PostRequest {
    String url;
    String body;
    Map<String, String> headers;

    @Override
    public String toString() {
      if(!url.contains("ChangeOwner")){
        return "";
      }
      StringBuffer head = new StringBuffer();
      headers.forEach((key, value) -> {
        head.append("  -H '" + key + ": " + value + "' \\\n");
      });
      head.append("  -H 'content-type: application/json' \\\n");

//      if (url.contains("object_data")) {
//        return "curl -X PUT \\\n" + "  '" + url.trim() + "' \\\n" + head.toString() + "  -d '" + body + "' \n";
//      } else {
        return "curl -X POST \\\n" + "  '" + url.trim() + "' \\\n" + head.toString() + "  -d '" + body + "' \n";
//      }
    }
  }

  @Test
  public void update_data() throws Exception {
    FileInputStream fis = new FileInputStream(SlowTest.class.getResource("/write_data.log").getPath());
    PrintWriter pw = new PrintWriter(new FileOutputStream(SlowTest.class.getResource("/result.log").getPath(), true));
    List<PostRequest> requests = Lists.newArrayList();
    readFileByLine(fis, (line) -> {
      String content = line.split("ms,")[2];
      List<String> temp = Splitter.on(",").splitToList(content);

      Map<String, String> headers = Maps.newHashMap();
      for (int i = 3; i < 12; i++) {
        String[] headerTemp = temp.get(i).split(":");
        try {
          headers.put(headerTemp[0], headerTemp[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
        }
      }
      headers.put(temp.get(13).split(":")[0], temp.get(13).split(":")[1].split("]")[0]);
      try {
        requests.add(PostRequest.builder().url(temp.get(0).split("url:")[1]).body(content.split("arg:")[1].split(",pathParams")[0]).headers(headers).build());
      } catch (Exception e) {
      }
    });
    for (PostRequest request : requests) {
      pw.println(request.toString());
    }
    System.out.println(requests.stream().map(item -> item.getHeaders().get("x-fs-ei")).collect(Collectors.toSet()));

    pw.close();
  }

  @Test
  public void exe_function() throws Exception {
    FileInputStream fis = new FileInputStream(SlowTest.class.getResource("/function.log").getPath());
    PrintWriter pw = new PrintWriter(new FileOutputStream(SlowTest.class.getResource("/result.log").getPath(), true));
    List<PostRequest> requests = Lists.newArrayList();
    readFileByLine(fis, (line) -> {
      String content = line.split("ms,")[2];
      List<String> temp = Splitter.on(",").splitToList(content);

      Map<String, String> headers = Maps.newHashMap();
      for (int i = 3; i < 12; i++) {
        String[] headerTemp = temp.get(i).split(":");
        try {
          headers.put(headerTemp[0], headerTemp[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
          System.out.println(e);
        }
      }
      headers.put(temp.get(13).split(":")[0], temp.get(13).split(":")[1].split("]")[0]);
      try {
        requests.add(PostRequest.builder().url(temp.get(0).split("url:")[1]).body(content.split("arg:")[1].split(",pathParams")[0]).headers(headers).build());
      } catch (Exception e) {
        System.out.println(e);
      }
    });
    for (PostRequest request : requests) {
      if(request.toString().length()>0)
      pw.println(request.toString());
    }
    System.out.println(requests.stream().map(item -> item.getHeaders().get("x-fs-ei")).collect(Collectors.toSet()));
    pw.close();
  }


  @Test
  public void startWorkflow() throws Exception {
    FileInputStream fis = new FileInputStream(SlowTest.class.getResource("/fix.log").getPath());
    PrintWriter pw = new PrintWriter(new FileOutputStream(SlowTest.class.getResource("/fixed_script.log").getPath(), true));
    readFileByLine(fis, (line) -> {
      String rst =
        "curl -X POST \\\n" + "  http://paas.nsvc.foneshare.cn/fs-paas-workflow/paas/crm/workflow/start \\\n" + "  -H 'accept-language: zh-CN' \\\n" +
          "  -H 'cache-control: no-cache' \\\n" + "  -H 'content-type: application/json' \\\n" +
          "  -H 'postman-token: adb70ff2-94ab-738a-1f84-e42cbaece791' \\\n" + "  -H 'x-fs-peer-host: 172.17.21.137' \\\n" +
          "  -H 'x-fs-peer-name: fs-workflow-fix' \\\n" + "  -d '" + line + "' \n";
      System.out.println(rst);
      pw.println(rst);
    });
    pw.close();
  }
}
