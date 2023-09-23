package com.java8.test.optional;

import java.util.Optional;
import java.util.OptionalInt;

public class OptionalTest {


    public static void test() {
        Integer i = null;
        // of 必须用在非空的变量
        //Optional.of(i).ifPresent(System.out::println);
        Optional.ofNullable(i)
                .ifPresent(System.out::println);

        int a = OptionalInt.of(9)
                .getAsInt();
    }

    public static void main(String[] args) {
        test();
    }
}
