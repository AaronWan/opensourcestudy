package com.aita.oop.classoop;

/**
 * @author 万松(Aaron)
 * @creat_date: 2021/9/2
 * @creat_time: 16:58
 * @since 7.5.0
 */
public enum SingletonEnum {
  instance;
  private String name;
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
