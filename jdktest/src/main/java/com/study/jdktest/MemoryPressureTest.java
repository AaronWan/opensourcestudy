package com.study.jdktest;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * @author 万松(Aaron)
 * @creat_date: 2020/6/27
 * @creat_time: 18:04
 * @since 7.2.0
 * -Xms1g -Xmm1g -XX:+UseG1GC -XX:InitialRAMPercentage=40.0 -XX:MaxRAMPercentage=40.0 -XX:+PrintGCDetails
 */
import java.util.ArrayList;
import java.util.List;

/**
 * -Xms20m -Xmx20m -XX:+UseG1GC -XX:InitialRAMPercentage=40.0 -XX:MaxRAMPercentage=40.0 -XX:InitiatingHeapOccupancyPercent=30
 */
public class MemoryPressureTest {

  public static void main(String[] args) {
    List<byte[]> memoryList = new ArrayList<>();

    try {
      System.out.println("Starting Memory Pressure Test...");

      // 模拟内存占用
      while (true) {
        byte[] memoryBlock = new byte[10 * 1024 * 1024]; // 10 MB
        memoryList.add(memoryBlock);

        // 当内存占用达到一定阈值时触发Full GC
        if (memoryList.size() >= 10) {
          System.out.println("Full GC triggered after reaching memory threshold.");
          memoryList.clear(); // 释放内存
        }

        Thread.sleep(100); // 稍微延迟以控制内存分配速度
      }
    } catch (OutOfMemoryError e) {
      System.out.println("Out of memory error occurred.");
    } catch (InterruptedException e) {
      System.out.println("Thread interrupted.");
    }
  }
}