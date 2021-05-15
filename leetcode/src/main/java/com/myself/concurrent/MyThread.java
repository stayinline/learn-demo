package com.myself.concurrent;

import java.util.concurrent.CyclicBarrier;

public class MyThread extends Thread {

    private String name;
    private CyclicBarrier cyclicBarrier;

    public MyThread(String name, CyclicBarrier cyclicBarrier) {
        super();
        this.name = name;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        System.out.println(name + "开始准备");
        try {
            Thread.sleep(2000);
            System.out.println(name + " 准备完毕！等待发令枪。");

            cyclicBarrier.await();

        } catch (Exception e) {
            System.out.println("等待出现异常！e=" + e.getMessage());
        }
    }
}
