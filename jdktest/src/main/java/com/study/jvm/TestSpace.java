package com.study.jvm;

public class TestSpace {
    private int a = 10;

    public long test(long num) {
        return this.a + num;
    }


    static {
        Runtime.getRuntime().addShutdownHook();
    }
}
