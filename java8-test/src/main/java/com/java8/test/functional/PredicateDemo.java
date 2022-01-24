package com.java8.test.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public class PredicateDemo {


    public static void main(String[] args) {
        testPredicate1();
        testPredicate2();
    }

    public static void testPredicate1() {
        Predicate<Integer> empty = (Integer s) -> s > 10;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Random().nextInt(20));
        }
        System.out.println("原始数组：" + list);
        List<Integer> result = filter(list, empty);
        System.out.println("过滤掉大于10的之后：" + result);
    }

    public static List<Integer> filter(List<Integer> list, Predicate<Integer> p) {
        List<Integer> result = new ArrayList<>();
        for (Integer item : list) {
            if (p.test(item)) {
                result.add(item);
            }
        }
        return result;
    }

    public static void testPredicate2() {


    }
}
