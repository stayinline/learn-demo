package com.myself.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestMain {

    private static int a = 0;


    public static void testVolitale() {
        for (int i = 0; i < 100; i++) {
            new Thread() {
                @Override
                public void run() {
                    try {

                        Thread.sleep(123);

                        a++;
                        Thread.sleep(123);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.run();
        }
        System.out.println("a=" + a);
    }

    public static void testThreadPool() {
        TimeUnit unit = TimeUnit.MILLISECONDS;
        BlockingQueue workQueue = new LinkedBlockingQueue();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20, 60, unit, workQueue);
        executor.execute(() -> System.out.println("test"));

        executor.shutdown();

    }

    public static void main(String[] args) {
//        testVolitale();
        testThreadPool();
    }
}
