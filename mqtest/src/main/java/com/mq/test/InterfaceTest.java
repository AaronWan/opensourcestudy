package com.mq.test;

public class InterfaceTest {

    interface A{
        default void testA() throws Exception{}
        static void testB(){

        }
    }

    class SubA implements A{
        @Override
        public void testA() throws NullPointerException{
            int a=10;
            long b=a;
        }
    }

}
