package com.opensource;

import com.google.common.base.Strings;

import java.time.*;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

/**
 * Created by Aaron on 16/06/2017.
 */
public class DateTimeUtil {
    public static LocalDate max(ChronoLocalDate date1, ChronoLocalDate date2) {
        return LocalDate.from(date1.isBefore(date2) ? date2 : date1);
    }

    public static LocalDate getLocalDate(Date date) {
        return LocalDate.from(date.toInstant());
    }

    public static Date getDate(ChronoLocalDate date) {
        LocalDate localDate = LocalDate.from(date);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    public static LocalDate getLocalDate(String dateStr, String formatStr) {
        if (Strings.isNullOrEmpty(dateStr)) {
            return null;
        }
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern(formatStr);
        TemporalAccessor temp = dtf.parse(dateStr);
        return LocalDate.from(temp);
    }

    public static String formateDate(LocalDate date,String formatStr){
        return date.format(DateTimeFormatter.ofPattern(formatStr));
    }

    public static LocalDate getNextNoWeekedDay(LocalDate today){
        while (today.getDayOfWeek() == DayOfWeek.SATURDAY || today.getDayOfWeek() == DayOfWeek.SUNDAY) {
            today = today.plusDays(1);
        }
        return today;
    }


}
