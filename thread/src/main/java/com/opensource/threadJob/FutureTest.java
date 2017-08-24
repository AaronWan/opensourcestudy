package com.opensource.threadJob;

import java.util.concurrent.*;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class FutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService= Executors.newFixedThreadPool(1);
        Future aa=  executorService.submit(new FutureTask(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "test";
            }
        }));
        executorService.shutdown();
        System.out.println(aa.get());
    }
}
