package com.myself.jvm;


import org.assertj.core.util.Lists;

import java.util.List;

public class GCLogDetail {


    /**
     * -XX:+PrintGC 输出GC日志
     * -XX:+PrintGCDetails 输出GC的详细日志
     * -XX:+PrintGCTimeStamps 输出GC的时间戳（以基准时间的形式）
     * -XX:+PrintGCDateStamps 输出GC的时间戳（以日期的形式，如 2017-09-04T21:53:59.234+0800）
     * -XX:+PrintHeapAtGC 在进行GC的前后打印出堆的信息
     * -Xloggc:../logs/gc.log 日志文件的输出路径
     *
     * @param args
     */

    public static void main(String[] args) {

        Thread.currentThread().setName("JVMTest");
        List<Integer> list = Lists.newArrayList();
        while (true) {
            list.add(1);
            System.out.println(Thread.currentThread().getName());
        }
    }
}
