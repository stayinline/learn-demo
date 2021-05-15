package com.myself.cas;

import java.util.concurrent.atomic.AtomicLong;

public class TestAutoMicLong {

    public static void main(String[] args) {
        AtomicLong atomicLong = new AtomicLong();

        long result = atomicLong.incrementAndGet();

        atomicLong.incrementAndGet();

        System.out.println(result);


    }
}
