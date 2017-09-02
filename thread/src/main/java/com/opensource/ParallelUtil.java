package com.opensource;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public class ParallelUtil {
    private static AtomicLong count = new AtomicLong();
    private static ThreadPoolExecutor executorService = new ThreadPoolExecutor(100, 100, 3000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(50),
                    new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            t.setName("ParallelUtil-"+count.getAndAdd(1));
            return t;
        }
    });

    public static ParallelTask createParalleTask(){
        return new ParallelTaskImpl();
    }

    interface ParallelTask {

        ParallelTask submit(Runnable runnable);
        boolean await(long seconds,TimeUnit timeUnit) throws InterruptedException;
    }

    static class ParallelTaskImpl implements ParallelTask {

        private List<Runnable> tasks= Lists.newArrayList();
        public ParallelTaskImpl() {

        }

        @Override
        public ParallelTask submit(Runnable runnable) {
            tasks.add(runnable);
            return this;
        }

        @Override
        public boolean await(long seconds, TimeUnit timeUnit) throws InterruptedException {
            AtomicBoolean rst=new AtomicBoolean(true);
            CountDownLatch countDownLatch=new CountDownLatch(tasks.size());
            for(Runnable runnable:tasks){
                executorService.submit(() -> {
                    try {
                        runnable.run();
                    }catch (RuntimeException e){
                        rst.getAndSet(false);
                        System.out.println(e.getMessage());
                    }finally {
                        countDownLatch.countDown();
                    }
                });
            }
            tasks.clear();
            countDownLatch.await(seconds,timeUnit);
            return rst.get();
        }
    }


    public static void main(String[] args) {
        ParallelTask parallelUtil=ParallelUtil.createParalleTask();
        final List<String> kk=Lists.newArrayList();
        for (int i = 0; i < 100; i++) {
            parallelUtil.submit(() -> kk.add(new Random(10).nextInt(20000) + ""));
        }
        try {
            parallelUtil.await(10,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(kk);
    }
}
