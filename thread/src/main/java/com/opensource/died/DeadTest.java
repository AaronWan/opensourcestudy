package com.opensource.died;

import lombok.SneakyThrows;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DeadTest {
    static ExecutorService e= Executors.newFixedThreadPool(1);
    @SneakyThrows
    public static void main(String[] args) {
        e.execute(()->{
            while (true){
                System.out.println("test ...");
            }
        });
        Thread.sleep(1000000);
    }
}