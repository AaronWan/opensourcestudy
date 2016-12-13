package com.opensource.clazz.impl;

import java.util.Arrays;

/**
 * Created by Aaron on 2016/10/10.
 */
public class B extends A<B> {
    public String test(String... arg ){
        System.out.println(Arrays.toString(arg));
        return Arrays.toString(arg);
    }
    public static void main(String[] args) {
        System.out.println(new B().test(null,null));
    }
}
