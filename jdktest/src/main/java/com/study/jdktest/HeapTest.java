package com.study.jdktest;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 万松(Aaron)
 * @creat_date: 2020/6/25
 * @creat_time: 11:03
 * @since 7.2.0
 * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError  -XX:HeapDumpPath=/home/hadoop/dump/
 */
public class HeapTest {
  public static void main(String[] args) {
    List<Map> data = Lists.newArrayList();
    for (; ; ) {
      data.add(new HashMap());
    }
  }
}
