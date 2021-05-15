package com.myself.leetcode;


import org.springframework.util.StopWatch;

/**
 * @author hemaoling
 */
public class StopWatchUtils {
    private static StopWatch stopWatch = new StopWatch();

    public static void start() {
        System.out.println("start !!!");
        stopWatch.start();
    }

    public static int stopAndGetTimeMillis() {
        System.out.println("end !!! spendTime:" + stopWatch.getTotalTimeMillis() + "ms");
        return 0;
    }
}
