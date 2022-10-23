package script;

import java.io.*;

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
        System.out.println("curl -X POST 'API/v1/inner/object/CasesCheckinsObj/action/FlowStartCallback?triggerFlow=false'   -H 'x-tenant-id:726368'  -H 'x-fs-userInfo:-10000' -H 'x-fs-ei:726368' -H 'x-user-id:-10000' -H 'x-peer-name:fs-crm-workflow-processor' -H 'FSR-GRAY_VALUE:726368' -H 'X-fs-RPC-Id:0.3' -H 'x-fs-locale:zh-CN' -H 'x-fs-peer-name:fs-crm-workflow-processor' -H 'X-fs-Trace-Id:8fa147d8-83d2-4ae3-a1d4-64fc6d3cd9ed' -H 'Accept-Language:zh-CN' -H 'X-fs-Enterprise-Id:726368' -H 'x-peer-ip:10.120.100.27' -H 'Content-Type:application/json'  -d '{\"describeApiName\":\"CasesCheckinsObj\",\"dataId\":"+data[i]+",\"triggerType\":\"1\",\"code\":301060001,\"callbackData\":{},\"triggerSynchronous\":false}'\n");
      }

    }
  }
}
