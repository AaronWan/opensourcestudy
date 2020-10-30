package com.study.jdktest;

import static java.lang.Thread.sleep;

/**
 * @author 万松(Aaron)
 * @creat_date: 2020/6/27
 * @creat_time: 18:04
 * @since 7.2.0
 * -Xms:20m -Xmm:20m -Xmn:4m -XX:+PrintGCDetails
 */
public class FullGCTest {
  public static final int _K=1024;
  public static void main(String[] args) throws InterruptedException {
    byte[] bytes=new byte[1*_K];
    for (int i=0;i<1000;i++){
      System.gc();
      sleep(5000);
    }
    bytes.hashCode();
  }
}
