package com.aita.throwable;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class TestCloseAble {
    public static void main(String[] args) {
        try(CloseAbleA a=new CloseAbleA();
            CloseAbleB b=new CloseAbleB()){
            System.out.println("do something");
        }catch ( Exception e  ){

        }
    }
}
