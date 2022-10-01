package com.java8.test.time;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeDemo {


    public static void main(String[] args) {


        String timeStr = "2022-09-14 15:00:00";
        ZoneId shanghai = ZoneId.of("Asia/Shanghai");
        ZoneId newYork = ZoneId.of("Asia/New_York");
        ZoneOffset zoneOffset = ZoneOffset.ofHours(9);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZonedDateTime date = ZonedDateTime.of(LocalDateTime.parse(timeStr), formatter.getZone());
        System.out.println(date);
    }
}
