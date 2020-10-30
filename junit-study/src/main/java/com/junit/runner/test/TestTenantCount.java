package com.junit.runner.test;

import com.google.common.collect.Maps;
import org.apache.commons.collections.MapUtils;
import scala.Int;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * @author 万松(Aaron)
 * @creat_date: 2019/3/6
 * @creat_time: 15:16
 * @since 6.4
 */
public class TestTenantCount {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(TestTenantCount.class.getResource("/data.txt").getPath())));
        String line=br.readLine();
        Map<String,Integer> rst= Maps.newHashMap();
        while (line!=null){
            Integer count=Integer.valueOf(line.split(" ")[0]);
            String tenantId=line.split(" ")[1];
            if(rst.get(tenantId)==null){
                rst.put(tenantId,count);
            }else{
                rst.put(tenantId,rst.get(tenantId)+count);
            }
            line=br.readLine();
        }
        AtomicInteger count=new AtomicInteger(0);
        rst.values().forEach(item-> count.addAndGet(item));
        AtomicInteger sub=new AtomicInteger();
        rst.forEach((key,value)->{
            if(value>10000){
                sub.addAndGet(value);
                System.out.println(key+"--"+value);
            }
        });

        System.out.println(count.get()-sub.get());
    }
}
