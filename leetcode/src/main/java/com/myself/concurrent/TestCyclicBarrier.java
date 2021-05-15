package com.myself.concurrent;

import java.util.concurrent.CyclicBarrier;

public class TestCyclicBarrier {

    public static void test() {
        int parties = 4;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(parties, () -> {
            System.out.println("发令枪响！");
        });
        for (int i = 0; i < parties; i++) {
            String name = "运动员" + i;
            new MyThread(name, cyclicBarrier).start();
        }
    }



    public static void main(String[] args) {

    }
}
