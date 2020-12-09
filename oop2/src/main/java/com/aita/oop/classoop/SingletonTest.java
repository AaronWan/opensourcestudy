package com.aita.oop.classoop;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class SingletonTest {
    public static void main(String[] args) {
        Some some=Some.create();
        Some some1=Some.create();
        Some some2 = new Some();
        System.out.println(some==some1);
        System.out.println(some==some2);
    }
}
