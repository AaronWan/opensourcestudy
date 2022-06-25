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
public class TriggerWorkflowMQReply
{

  @Test
  public void update_message() throws Exception {
    String result="curl 'https://oss.xxx.cn/rmq-console-4167/message/consumeMessageDirectly.do?consumerGroup=FLOW-QUARTZ-BATCH-VIP-C&msgId=AC11294500002A9F00000C6EFB977B81&topic=FLOW-QUARTZ-BATCH-VIP' \\\n" +
            "  -X 'POST' \\\n" +
            "  -H 'authority: oss.foneshare.cn' \\\n" +
            "  -H 'accept: application/json, text/plain, */*' \\\n" +
            "  -H 'accept-language: zh-CN,zh;q=0.9,en;q=0.8' \\\n" +
            "  -H 'cache-control: no-cache' \\\n" +
            "  -H 'content-length: 0' \\\n" +
            "  -H 'cookie: NG_TRANSLATE_LANG_KEY=%22en%22; jsig=NjdCOEVCMDU4NzU2QURBRTQ0N0JEMDI2OTEyREI5QjY%3D; _fio-admin-server_sid=s%3AJQJm8OICFdagT6HJrp6TQN375SmM31yK.qwdxIRSNe3Xux6EJgGujFtfZgPw8qm8QGYYBi8oPXZk; fio-admin-auth=1; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2215110130731%22%2C%22%24device_id%22%3A%2217efb8ae3d216fa-0e04f686ef6849-133f685c-2073600-17efb8ae3d3182e%22%2C%22props%22%3A%7B%22%24latest_referrer%22%3A%22%22%2C%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer_host%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTgwZDZhOGFkNjMxMGFlLTBiMWE0OGU2ZWQzZjZiOC0zNDcwNjcwNC0yMDczNjAwLTE4MGQ2YThhZDY0MWUzNSIsIiRpZGVudGl0eV9sb2dpbl9pZCI6IjE1MTEwMTMwNzMxIn0%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%2215110130731%22%7D%2C%22first_id%22%3A%2217efb8ae3d216fa-0e04f686ef6849-133f685c-2073600-17efb8ae3d3182e%22%7D' \\\n" +
            "  -H 'origin: https://oss.foneshare.cn' \\\n" +
            "  -H 'pragma: no-cache' \\\n" +
            "  -H 'referer: https://oss.foneshare.cn/rmq-console-4167/' \\\n" +
            "  -H 'sec-ch-ua: \" Not A;Brand\";v=\"99\", \"Chromium\";v=\"101\", \"Google Chrome\";v=\"101\"' \\\n" +
            "  -H 'sec-ch-ua-mobile: ?0' \\\n" +
            "  -H 'sec-ch-ua-platform: \"macOS\"' \\\n" +
            "  -H 'sec-fetch-dest: empty' \\\n" +
            "  -H 'sec-fetch-mode: cors' \\\n" +
            "  -H 'sec-fetch-site: same-origin' \\\n" +
            "  -H 'user-agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.0.0 Safari/537.36' \\\n" +
            "  --compressed";
    FileInputStream fis = new FileInputStream(SlowTest.class.getResource("/msgIds.log").getPath());
    readFileByLine(fis, (line) -> {
      System.out.println(result.replace("AC11294500002A9F00000C6EFB977B81",line));
    });
  }


}
