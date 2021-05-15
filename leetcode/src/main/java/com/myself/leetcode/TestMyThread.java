package com.myself.leetcode;

public class TestMyThread implements Runnable {


    public void run(Integer q) throws InterruptedException {
        Thread.sleep(1000);



        System.out.println("我这个线程要做的事情");

        int a = 10;
        int b = 20;
        int c = a + b;

        ThreadLocal<String> local= new ThreadLocal<>();

        local.set("1G");

        local.remove();


    }

    @Override
    public void run() {

    }
}
