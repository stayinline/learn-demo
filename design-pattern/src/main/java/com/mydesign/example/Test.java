package com.mydesign.example;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static com.sun.tools.internal.xjc.reader.Ring.add;

public class Test {

    private static int THREAD_COUNT = 10;

    private static int ITEM_COUNT = 1000;

//    private ConcurrentHashMap<String, Long> getData(int count) {
//        return LongStream.rangeClosed(1, count)
//                .boxed()
//                .collect(Collectors.toConcurrentMap((i -> UUID.randomUUID().toString(), (o1, o2) -> o1, ConcurrentHashMap::new)));
//    }

    public static void main(String[] args) {
        System.out.println("asda");


    }
}
