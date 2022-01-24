package test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class Test {

    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        System.out.println(now);

        System.out.println(now.with(TemporalAdjusters.firstDayOfMonth()));
        System.out.println(now.with(TemporalAdjusters.lastDayOfYear()));

        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
    }
}
