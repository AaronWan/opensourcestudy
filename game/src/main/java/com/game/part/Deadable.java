package com.game.part;

import com.game.Part;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 万松(Aaron)
 * @since 5.7
 */
public interface Deadable {
    ExecutorService executorService = Executors.newCachedThreadPool();
    default void start(Runnable task){
        executorService.submit(task);
    }

    Part.Stat checkState();

    void setStat(Part.Stat stat);
}
