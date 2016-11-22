package com.opensource.plan;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class PlanTest {
    public void beepForAnHour() {
        final Runnable beeper = () -> System.out.println("beep");

        final ScheduledFuture<?> beeperHandle =
                scheduler.scheduleAtFixedRate(beeper, 10, 10, TimeUnit.SECONDS);

        scheduler.schedule((Runnable) () -> beeperHandle.cancel(true), 60, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        new PlanTest().beepForAnHour();
    }

    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);


}
