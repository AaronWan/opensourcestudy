package com.aita.oop.classoop;

import java.util.Arrays;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class Some {

    private static Some some;

    public Some() {
    }
    public static Some create(){
        if(some==null){
            some=new Some();
        }
        return some;
    }
}
