package com.junit.runner.test.hamcrest;

import com.google.common.collect.Maps;
import com.junit.runner.test.TestTenantCount;

import java.io.*;
import java.util.Map;

/**
 * @author 万松(Aaron)
 * @creat_date: 2019/4/3
 * @creat_time: 22:16
 * @since 6.6
 */
public class CandidateIdsTest {
  public static void main(String[] args) throws IOException {
    BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(TestTenantCount.class.getResource("/sort.txt").getPath())));
    String line=br.readLine();
    Map<String,Double> rst= Maps.newHashMap();
    while (line!=null){
      Double count=Double.valueOf(line.split(" ")[1]);
      String number=line.split(" ")[0];
      rst.put(number,count);
      line=br.readLine();
    }
    rst.forEach((k, v)->{
      System.out.printf("%s %s ",k,v);
      System.out.println();
    });
  }
}
