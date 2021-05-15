package com.myself.concurrent;

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

    public static void main(String[] args) {
        testVolitale();
    }
}
