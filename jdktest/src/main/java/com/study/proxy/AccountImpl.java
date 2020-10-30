package com.study.proxy;

/**
 * @author 万松(Aaron)
 * @creat_date: 2020/10/2
 * @creat_time: 10:32
 * @since 7.2.5
 */
public class AccountImpl implements Account{
  private String name;
  private int age;
  @Override
  public String getName() {
    return name;
  }
  @Override
  public void setName(String name) {
    this.name = name;
  }
  @Override
  public int getAge() {
    return age;
  }
  @Override
  public void setAge(int age) {
    this.age = age;
  }
}
