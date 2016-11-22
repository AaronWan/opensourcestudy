package com.opensource.enumtest;

/**
 * Created by Aaron on 16/8/15.
 */
public class EnumTest {
    enum PersonType{
        A,B,C,D;
    }

    public static void main(String[] args) {
        System.out.println(PersonType.A.ordinal());
        System.out.println(PersonType.B.ordinal());
    }
}
