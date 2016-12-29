package com.opensource.clazz;

/**
 * Created by Aaron on 15/12/2016.
 */
public class TestClassMethod {
    static class D{

    }
    static class E extends D{

    }

    public static void main(String[] args) {
        System.out.println(D.class.isAssignableFrom(E.class));
        System.out.println(D.class.isInstance(new E()));
    }
}
