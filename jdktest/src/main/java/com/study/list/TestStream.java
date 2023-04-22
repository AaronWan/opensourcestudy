package com.study.list;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

/**
 * @author wansong
 * @date 2023/4/21
 * @apiNote
 **/
public class TestStream {
    static List<String> samples= Lists.newArrayList();
    static {
        for (int i=0;i<1000;i++) {
            samples.add(UUID.randomUUID().toString());
        }
    }
    @Test
    public void testSample(){
        long asyncTime = testAsync();
        long syncTime =testSync();
        System.out.println(syncTime-asyncTime);
        System.out.println(testTT()-asyncTime);
    }
    public long testTT(){
        long start=0;
        for (String item : samples) {
        }
        return (System.currentTimeMillis() - start);
    }
    public long testSync(){
        long start=0;
        samples.stream().forEach(item->{

        });
        return (System.currentTimeMillis() - start);
    }

    public long testAsync(){
        long start=0;
        samples.stream().parallel().forEach(item->{

        });
        return (System.currentTimeMillis() - start);
    }
}
