package com.java8.test.time;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TimeDemo {


    public static void main(String[] args) {


        //testTime();

        testMapCap();
    }

    private static void testMapCap() {
        Map<Integer, String> map = new ConcurrentHashMap<>(32);

        for (int i = 0; i < 100; i++) {
            map.put(i, "i=" + i);
        }

        System.out.println(map.keySet().size()); // 100,不会报错
    }

    private static void testTime() {
        String timeStr = "2022-09-14 15:00:00";
        ZoneId shanghai = ZoneId.of("Asia/Shanghai");
        ZoneId newYork = ZoneId.of("Asia/New_York");
        ZoneOffset zoneOffset = ZoneOffset.ofHours(9);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZonedDateTime date = ZonedDateTime.of(LocalDateTime.parse(timeStr), formatter.getZone());
        System.out.println(date);
    }
}
