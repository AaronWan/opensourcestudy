package com.aita.oop.practices;

import com.aita.oop.Other;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class Some {
    protected int x;

    public Some() {
        this(10);
        System.out.println("Some()");
    }

    public Some(int x) {
        this.x = x;
        System.out.println("Same("+x+")");
    }

    public void doSomething(){
        System.out.println("do Some");
    }

    public static void main(String[] args) {
        Some some=new Other(10);
    }
}


