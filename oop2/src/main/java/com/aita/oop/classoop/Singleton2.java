package com.aita.oop.classoop;

/**
 * @author 万松(Aaron)
 * @creat_date: 2021/9/2
 * @creat_time: 16:58
 * @since 7.5.0
 */
public class Singleton2 {

  private static Singleton2 instance;
  private String name;

  private Singleton2() {

  }

  public static synchronized Singleton2 getInstance() {
    if (instance == null) {
      instance = new Singleton2();
    }
    return instance;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
