package com.myself.concurrent;

public class TestThread implements Runnable {

    private int a;

    public TestThread(int a) {
        this.a = a;
    }

    @Override
    public void run() {
        a++;
        System.out.println("in thread a=" + a);
    }
}
