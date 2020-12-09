package com.aita.element.hello;

/**
 * Created by Aita on 17/9/23.
 */
class HelloA {
    public HelloA(){
        System.out.println("HelloA");
    }
    {
        System.out.println("I am A class");
    }
    static {
        System.out.println("static A");
    }
}

public class HelloB extends HelloA{
    public HelloB(){
        System.out.println("HelloB");
    }
    {
        System.out.println("I am B class");
    }

    static {
        System.out.println("static B");
    }
    public static void main(String[] args){
        new HelloB();
    }
}
