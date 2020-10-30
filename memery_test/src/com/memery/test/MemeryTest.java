package com.memery.test;

import sun.misc.Cleaner;
import sun.nio.ch.DirectBuffer;

import java.nio.ByteBuffer;

/**
 * @author 万松(Aaron)
 * @creat_date: 2020/6/5
 * @creat_time: 11:48
 * @since 7.2.0
 */
public class MemeryTest {
  public static void main(String[] args) throws InterruptedException {
    useDirectMemery();
  }
  public static void useDirectMemery() throws InterruptedException {
    ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 10);
    System.out.printf(buffer.isDirect()+"");
    Thread.sleep(1000000);
  }
}
