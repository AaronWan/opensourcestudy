package com.opensource.clazz.impl;

/**
 * Created by Aaron on 2016/10/10.
 */
public class A<B> {
    public A() {
        System.out.println(this.getClass().getAnnotatedSuperclass().getType());
    }
}
