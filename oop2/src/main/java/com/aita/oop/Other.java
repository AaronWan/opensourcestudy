package com.aita.oop;

import com.aita.oop.practices.Some;

public class Other extends Some {

    public Other() {
        super(10);
        System.out.println("Other()");
    }

    public Other(int x) {
        System.out.println("Other("+x+")");
    }

    public void doSomething(){

    }
}