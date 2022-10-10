package com.study.jdktest;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Map;

/**
 * @author 万松(Aaron)
 * @creat_date: 2019-10-10
 * @creat_time: 13:45
 * @since 6.6
 */
public class JdkTest {
  @Test
  public void testMap() {
    Map<String, String> data = Maps.newHashMap();
    for (int i = 0; i < 1; i++) {
      data.put(i + "", i + "");
    }
    System.out.println(data);
  }
}
