package com.study.elasticsearch.script;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 万松(Aaron)
 * @creat_date: 2020/6/11
 * @creat_time: 08:48
 * @since 7.2.0
 */
public class ScriptTest {
  /**
   * 解析出有问题的求点
   * @param args
   */

  public static void main(String[] args) throws Exception{
//    String test="methodReportFail:true,retry:0,httpCode:0,time:10070 ms,socketTimeOutConfig:10000 ms,url: http://crmrestful.nsvc.foneshare.cn/crm/AddRemindRecord ,method:POST,headerParams:[x-fs-ei:691413,X-fs-Trace-Id:fs-crm-task-sfa/03918521-101f-46,Accept-Language:zh-CN,x-peer-ip:10.133.155.11,x-peer-name:fs-crm-task-sfa,Content-Type:application/json],arg:{\"EmployeeID\":\"-10000\",\"Content\":\"姓名：李dXuUe\",\"RemindRecordType\":1,\"Content2ID\":\"0\",\"DataID\":\"5edfae2e5c173a0001bb8b0a\",\"ReceiverIDs\":[1000,1003]},pathParams:{},queryParams:{},result:null\"\"\"";
//    System.out.println(test.split("url:|,method:")[1]);
    BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(ScriptTest.class.getResource("/data").getPath())));
    String line;
    Set<String> result=new HashSet<>();
    while ((line =br.readLine())!=null){
      String[] rst=line.split("/");
      result.add(rst[0]+"/"+rst[1]+"/"+rst[2]);
    }
    result.stream().forEach(item-> System.out.println(item));
  }
}
