package com.study.thread;
/**
 * @author wansong
 * @date 2023/7/2
 * @apiNote
 * -XX:MaxThreads 或 -XX:MaxJavaThreads
 **/
public class ThreadBeyondMaxOOM {
    public static void main(String[] args) {
        try {
            while (true) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            // Perform some task
                        }
                    }
                }).start();
            }
        } catch (OutOfMemoryError e) {
            System.out.println("Out of Memory Error: " + e.getMessage());
        }
    }
}
