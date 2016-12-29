package com.opensource.threadJob;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Aaron on 22/11/2016.
 */
public class JobManage {
    private ConcurrentMap<String, Task> tasks = new ConcurrentHashMap<>();
    private ExecutorService executorService = Executors.newCachedThreadPool();

    public void submit(Task task) {
        tasks.put(task.getId(),task);
        executorService.submit(task);
    }

    public void stop(String taskId){
        tasks.get(taskId).setStop();
    }
    public void stopAll(){
        executorService.shutdown();
    }
    public Task getTaskProcess(String taskId) {
        return tasks.get(taskId);
    }

}
