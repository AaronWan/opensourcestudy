package com.study.asm;

/**
 *  javac -g TestASM.java javap -verbose TestASM.class
 */
public class TestASM {
    private int num1 = 1;
    public static int NUM1 = 100;
    public int func(int a,int b){
        return add(a,b);
    }
    public int add(int a,int b) {
        return a+b+num1;
    }
    public int sub(int a, int b) {
        return a-b-NUM1;
    }

    public static void main(String[] args) {
        System.out.println(new TestASM().add(20,30));
    }
}
