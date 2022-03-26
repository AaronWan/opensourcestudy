package com.aita.oop.school;

/**
 * @author 万松(Aaron)
 * @creat_date: 2021/9/2
 * @creat_time: 16:31
 * @since 7.5.0
 */
public class Student extends Person {
  static {
    System.out.println("Student 静态块");
  }

  {
    System.out.println("Student 块");
  }

  public Student() {
    System.out.println("Student 空构造");
  }

  @Override
  protected void finalize() {
    System.out.println("Student finalize");
  }

  public static void main(String[] args) throws InterruptedException {
    System.out.println(new Student().getAge());
    System.gc();
    Thread.sleep(10000);
  }

  /**
   * Person 静态块
   * Student 静态块
   * Person 块
   * Person 空构造
   * Student 块
   * Student 空构造
   * 0
   * Student finalize
   */
}
