package com.java8.test.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author hemaoling
 */
public class TestStream {
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>(10000000);
        for (int i = 0; i < 10000000; i++) {
            list.add(i);
        }
        long start = System.currentTimeMillis();
        Long sum = list.stream().mapToLong(x->x).sum();
        long end = System.currentTimeMillis();
        System.out.println("sum=" + sum + " 单行流耗时：" + (end - start));


        long start1 = System.currentTimeMillis();
        Long sum1 = list.parallelStream().mapToLong(x->x).sum();
        long end1 = System.currentTimeMillis();
        System.out.println("sum1=" + sum1 + " 并行流耗时：" + (end1 - start1));

        long start2 = System.currentTimeMillis();
        AtomicReference<Long> sum2 = new AtomicReference<>();
        new ForkJoinPool(6).submit(()->{
          sum2.set(list.parallelStream().mapToLong(x -> x).sum());
        }).join();
        long end2 = System.currentTimeMillis();
        System.out.println("sum2=" + sum2 + " 自定义并行流耗时：" + (end2 - start2));

    }
}
