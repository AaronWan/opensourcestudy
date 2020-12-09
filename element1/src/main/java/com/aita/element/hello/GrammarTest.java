package com.aita.element.hello;

/**
 * Created by Aita on 17/9/23.
 */
public class GrammarTest {
    public static void GrammarTest(){
      //  short s1=1; s1=s1+1; //存在从int转换到short的问题
      //  int s1=1; s1=s1+1;
        short s1=1; s1+=1;
        System.out.println(s1);
    }

    public static void main(String[] args) {
        System.out.println("hehe");
        GrammarTest();
    }
}
