package com.junit.runner.test;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author 万松(Aaron)
 * @creat_date: 2019/1/1
 * @creat_time: 11:08
 * @since 6.4
 */
public class TimeoutAndException {

    @Test(timeout = 1010)
    public void testTimeout() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
    }

    @Test(expected = RuntimeException.class)
    public void testException()  {
        throw new RuntimeException("sd");
    }
}

