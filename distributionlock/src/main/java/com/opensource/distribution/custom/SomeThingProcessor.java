package com.opensource.distribution.custom;

/**
 * Created by Aaron on 16/8/14.
 */
public class SomeThingProcessor {
    String thread_key="SomeThingProcessor--"+System.currentTimeMillis();
    private FetchTokenManager fetchTokenManager = new FetchTokenManager(Thread.currentThread().getName()+"_"+thread_key);

    public SomeThingProcessor() {
    }

    public void process() {
        new Thread(thread_key) {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }
                    if (fetchTokenManager.canProcess()) {
                        System.out.println(thread_key);
                    }
                }
            }
        }.start();
    }
}
