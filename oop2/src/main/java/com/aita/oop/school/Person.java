package com.aita.oop.school;

/**
 * @author 万松(Aaron)
 * @creat_date: 2021/9/2
 * @creat_time: 16:31
 * @since 7.5.0
 */
public class Person {
  private int age;

  {
    System.out.println("Person 块");
  }

  static {
    System.out.println("Person 静态块");
  }

  public Person() {
    System.out.println("Person 空构造");
  }

  public Person(int age) {
    this.age = age;
    System.out.println("Person 带参构造");
  }

  public int getAge() {
    return age;
  }

  @Override
  protected void finalize() {
    System.out.println("Person finalize");
  }
}
