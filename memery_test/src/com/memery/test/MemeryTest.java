package com.memery.test;


import java.nio.ByteBuffer;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 万松(Aaron)
 * @creat_date: 2020/6/5
 * @creat_time: 11:48
 * @since 7.2.0
 */
public class MemeryTest {
  public static void main(String[] args) throws InterruptedException {
    new MemeryTest().createObj();
    Thread.sleep(1000000);
  }
  public Map map=new ConcurrentHashMap<>();

  public void createObj() throws InterruptedException {
    for (int i = 0; i < 20000000; i++) {
      map.put(i,new TestHeapBean("name"+i,i));
      useDirectMemery();
    }

  }
  class TestHeapBean{
    private String name;
    private int age;

    public TestHeapBean(String name, int age) {
      this.name = name;
      this.age = age;
    }
  }
  public static void useDirectMemery() throws InterruptedException {
    ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 10);
  }
}

