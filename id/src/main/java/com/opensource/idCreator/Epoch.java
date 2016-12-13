package com.opensource.idCreator;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Epoch {

    private static TimeZone TIME_ZONE = TimeZone.getTimeZone("GMT");

    public static long getEpoch(int year,int month,int day){

        Calendar calendar = Calendar.getInstance(TIME_ZONE);
        calendar.set(year, month, day,0,0,0);
        calendar.set(Calendar.MILLISECOND,0);

        long timeInMillis = calendar.getTimeInMillis();

        return (timeInMillis > 0) ? timeInMillis : 0;
    }

    public static int yearNow(){
        Calendar calendar = Calendar.getInstance(TIME_ZONE);
        calendar.setTime(new Date());

        return calendar.get(Calendar.YEAR);
    }

    public static void main(String[] args){

        System.out.println(yearNow());
    }
}