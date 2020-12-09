package com.aita.element.hello;

/**
 * Created by Aita on 17/9/14.
 */
public class TryCatchFinal {
    public static int func(){
        try{
            return 1;
        }catch(Exception e){
            return 2;
        }finally {
            return 3;
        }
    }
}


