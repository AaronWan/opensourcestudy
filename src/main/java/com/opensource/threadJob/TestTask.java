package com.opensource.threadJob;

/**
 * Created by Aaron on 22/11/2016.
 */
public class TestTask {
    public static void main(String[] args) throws InterruptedException {
        JobManage jobManage=new JobManage();
        Task task;
        jobManage.submit(task=new Task());

        for(int i=0;i<1000;i++){
            Thread.sleep(1000);
            System.out.println(task.getId()+"--"+jobManage.getTaskProcess(task.getId()).getProcess());
            if(i==200){
                jobManage.stop(task.getId());
                System.exit(0);
            }
        }
    }
}
