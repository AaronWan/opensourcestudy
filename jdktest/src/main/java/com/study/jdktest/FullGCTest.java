package com.study.jdktest;

import com.google.common.collect.Lists;

import java.util.List;

import static java.lang.Thread.sleep;

/**
 * @author 万松(Aaron)
 * @creat_date: 2020/6/27
 * @creat_time: 18:04
 * @since 7.2.0
 * -Xms20m -Xmm20m -Xmn4m -XX:+PrintGCDetails
 */
public class FullGCTest {
  public static final int _K=1024;
  public static void main(String[] args) throws InterruptedException {
    useMetaspace();
  }

  public static void useHeap() throws InterruptedException {
    byte[] bytes=new byte[1*_K];
    for (int i=0;i<1000;i++){
      System.gc();
      sleep(5000);
    }
    bytes.hashCode();
  }

  public static void useMetaspace() throws InterruptedException {
    List<String> kk= Lists.newArrayList();
    for (int i = 0; i <1000 ; i++) {
      kk.add("sdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdf"+i);
      sleep(5000);
      System.gc();
    }
  }
}
