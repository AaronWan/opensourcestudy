package com.study.jdktest;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author 万松(Aaron)
 * @creat_date: 2020/6/25
 * @creat_time: 10:52
 * @since 7.2.0
 * -Xss512k
 */
public class StackTest {

  public static final AtomicLong count=new AtomicLong();
  public void testStack(){
    System.out.println(count.incrementAndGet());
    testStack();
  }

  public static void main(String[] args) {
    new StackTest().testStack();
  }
}
