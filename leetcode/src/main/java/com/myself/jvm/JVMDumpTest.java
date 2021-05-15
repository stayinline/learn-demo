package com.myself.jvm;


import org.assertj.core.util.Lists;

import java.util.List;

public class JVMDumpTest {

    public static void main(String[] args) {
        Thread.currentThread().setName("JVMTest");
        List<Integer> list = Lists.newArrayList();
        while (true) {
            list.add(1);
            System.out.println(Thread.currentThread().getName());
        }

//        Thread thread = new Thread(()-> System.out.println("deal......"));
//        thread.setName("myTestThread");
//        thread.start();
//        while (true) {
//            System.out.println("213");
//        }
    }
}
