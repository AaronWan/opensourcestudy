package com.study.jdktest;

import lombok.Data;

/**
 * @author 万松(Aaron)
 * @creat_date: 2020/6/25
 * @creat_time: 15:17
 * @since 7.2.0
 * -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+UseG1GC
 */
public class GCOfCount {
  @Data
  public static class Person{
    private Person child;
    private String name;

    public Person(String name) {
      this.name = name;
    }
  }

  public static void main(String[] args) {
    Person person1=new Person("a");
    Person person2=new Person("b");
    person1.child=person2;
    person2.child=person1;
    System.gc();
    System.out.println(person1.getName());
  }
}
