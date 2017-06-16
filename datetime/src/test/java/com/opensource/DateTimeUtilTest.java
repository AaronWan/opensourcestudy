package com.opensource;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.time.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Aaron on 16/06/2017.
 */
public class DateTimeUtilTest {
    @Test
    public void testZones() {
        Collection<String> allZones = ZoneId.getAvailableZoneIds();
        List<String> allZoneList = Lists.newArrayList(allZones);
        Collections.sort(allZoneList);
        allZoneList.forEach(zone -> System.out.println(zone));
    }

    @Test
    public void testLocalDateTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.now());
        List<String> allZoneList = Lists.newArrayList(ZoneId.getAvailableZoneIds());
        Collections.sort(allZoneList);
        allZoneList.forEach(zone -> {
            ZonedDateTime zdt = localDateTime.atZone(ZoneId.of(zone));
            ZoneOffset offset = zdt.getOffset();
            int secondsOfHour = offset.getTotalSeconds() % (60 * 60);
            String out = String.format("%35s %10s%n", zone, offset);
            if (secondsOfHour != 0) {
                System.out.printf(out);
            }
        });
    }

    public LocalDateTime zoonDatetime(LocalDateTime dateTime, ZoneId fromZoneId, ZoneId toZoneId) {
        ZonedDateTime departure = ZonedDateTime.of(dateTime, fromZoneId);
        System.out.println(departure);
        ZonedDateTime arrival = departure.withZoneSameInstant(toZoneId);
        return arrival.toLocalDateTime();
    }

    @Test
    public void testZonedDateTime(){
        System.out.println(LocalDateTime.now());
        System.out.println(zoonDatetime(LocalDateTime.now(),ZoneId.of("America/Edmonton"),ZoneId.of("Asia/Shanghai")));
    }
}
