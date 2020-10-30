package com.study.jdktest;

import sun.misc.Unsafe;

/**
 * @author 万松(Aaron)
 * @creat_date: 2020/6/23
 * @creat_time: 22:49
 * @since 7.2.0
 */
public class AtomicIntegerTest {
  public static void main(String[] args) throws SecurityException {
    final Unsafe unsafe = Unsafe.getUnsafe();
    System.out.println(unsafe.getAndAddInt(Unsafe.getUnsafe(), 1L, 1));
  }
}
