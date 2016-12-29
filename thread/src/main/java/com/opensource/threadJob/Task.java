package com.opensource.threadJob;

import lombok.Data;

import java.util.UUID;

/**
 * Created by Aaron on 22/11/2016.
 */
@Data
public class Task implements Runnable{
    private String id= UUID.randomUUID().toString();
    private int process=0;
    private boolean stop;
    @Override
    public void run() {
        while (process<100){
            if(stop){
                break;
            }
            process++;
            try {
                Thread.sleep(1000);
            }catch (Exception e){}
        }
    }

    public void setStop() {
        stop=true;
    }
}
