package com.study.date;

import org.junit.Test;

import java.util.Calendar;
import java.util.Locale;

import static java.util.Calendar.SHORT_FORMAT;

/**
 * @author 万松(Aaron)
 * @creat_date: 2021/4/26
 * @creat_time: 07:32
 * @since 7.5.0
 */
public class DateTest {
  @Test
  public void testDayOfWeek() {
    Calendar calendar = Calendar.getInstance(Locale.FRANCE);
    for (int i = 1; i < 8; i++) {
      calendar.set(Calendar.DAY_OF_WEEK,i);
      System.out.print("当前是周几: " + calendar.getDisplayName(Calendar.DAY_OF_WEEK, SHORT_FORMAT, Locale.CHINA));
      calendar.add(Calendar.DATE, -calendar.get(Calendar.DAY_OF_WEEK) - 1);
      System.out.print("\t获取上周五: " + calendar.getDisplayName(Calendar.DAY_OF_WEEK, SHORT_FORMAT, Locale.CHINA));
      System.out.println("\t "+calendar.get(Calendar.DAY_OF_WEEK)+"\t");
    }
  }
}
