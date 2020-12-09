package com.aita.element.hello;

/**
 * Created by Aita on 17/9/23.
 */
public class TryCatchFinallyFunc {
    public static int func(){
        try{
            System.out.println("try");
            return 1;
        }catch(Exception e){
            System.out.println("when catch exception e");
            return 2;
        }finally{
            System.out.println("finally");
            return 3;
        }
    }

    public static void main(String[] args) {
        System.out.println(func());
    }
}
