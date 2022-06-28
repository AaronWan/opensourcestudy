package com.aita.oop.classoop;

/**
 * @author 万松(Aaron)
 * @creat_date: 2021/9/2
 * @creat_time: 16:58
 * @since 7.5.0
 */
public class Singleton3 {

  private static final Singleton3 instance=new Singleton3();
  private String name;

  private Singleton3() {

  }

  public static Singleton3 getInstance() {
    return instance;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
