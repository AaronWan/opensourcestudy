package com.mq.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {
    public static void main(String[] args) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(1639648874996l)));
    }
}
