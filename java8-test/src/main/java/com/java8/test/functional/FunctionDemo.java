package com.java8.test.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FunctionDemo {

    public static void main(String[] args) {
        testFunction1();
    }

    private static void testFunction1() {
        List<String> list = new ArrayList<>();
        list.add("java");
        list.add("go");
        list.add("python");
        System.out.println("原始数组：" + list);
        List<Integer> result = map(list, (String s) -> s.length());
        System.out.println("每个元素的长度：" + result);
    }

    public static List<Integer> map(List<String> list, Function<String, Integer> f) {
        List<Integer> result = new ArrayList<>();
        for (String item : list) {
            result.add(f.apply(item));
        }
        return result;
    }
}
