package com.java8.test.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @author hemaoling
 */
public class TestStream {


    private static void test2() {
        List<Integer> list = new ArrayList<>(10000000);
        for (int i = 0; i < 10000000; i++) {
            list.add(i);
        }
        long start = System.currentTimeMillis();
        Long sum = list.stream().mapToLong(x -> x).sum();
        long end = System.currentTimeMillis();
        System.out.println("sum=" + sum + " 单行流耗时：" + (end - start));


        long start1 = System.currentTimeMillis();
        Long sum1 = list.parallelStream().mapToLong(x -> x).sum();
        long end1 = System.currentTimeMillis();
        System.out.println("sum1=" + sum1 + " 并行流耗时：" + (end1 - start1));

        long start2 = System.currentTimeMillis();
        AtomicReference<Long> sum2 = new AtomicReference<>();
        new ForkJoinPool(6).submit(() -> {
            sum2.set(list.parallelStream().mapToLong(x -> x).sum());
        }).join();
        long end2 = System.currentTimeMillis();
        System.out.println("sum2=" + sum2 + " 自定义并行流耗时：" + (end2 - start2));
    }

    public static void test() {
        List<Integer> numbers = Arrays.asList(2, 3, 4, 5);
        List<Integer> result =
                numbers.stream()
                        .peek(x -> System.out.println("from stream: " + x))
                        .map(x -> x + 17)
                        .peek(x -> System.out.println("after map: " + x))
                        .filter(x -> x % 2 == 0)
                        .peek(x -> System.out.println("after filter: " + x))
                        .limit(3)
                        .peek(x -> System.out.println("after limit: " + x))
                        .collect(Collectors.toList());
        /**
         * 输出结果：
         * from stream: 2
         * after map: 19
         *   19%2 !=0 校验不通过，所以不能通过"after filter"输出
         *
         * from stream: 3
         * after map: 20
         * after filter: 20
         * after limit: 20
         *
         *
         * from stream: 4
         * after map: 21
         *
         *
         * from stream: 5
         * after map: 22
         * after filter: 22
         * after limit: 22
         */


    }

    public static void main(String[] args) {

        //test2();
        test();
    }
}
