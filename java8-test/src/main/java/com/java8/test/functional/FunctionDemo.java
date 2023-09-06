package com.java8.test.functional;

import com.sun.tools.javac.util.StringUtils;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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

        // List<Object> collect = list.stream().flatMap(x -> x.split("")).distinct().collect(Collectors.toList());
        List<String> collect = list.stream()
                .map(x -> x.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(collect);

    }

    private static void testFunction13() {


    }


    public static List<Integer> map(List<String> list, Function<String, Integer> f) {
        List<Integer> result = new ArrayList<>();
        for (String item : list) {
            result.add(f.apply(item));
        }
        return result;
    }


    public static class Letter {
        public static String addHeader(String text) {
            return "From Raoul, Mario and Alan: " + text;
        }

        public static String addFooter(String text) {
            return text + " Kind regards";
        }

        public static String checkSpelling(String text) {
            return text.replaceAll("labda", "lambda");
        }
    }

    private static void testFunction2() {
        Function<String, String> addHeader = Letter::addHeader;
        Function<String, String> transformationPipeline
                = addHeader.andThen(Letter::checkSpelling)
                .andThen(Letter::addFooter);

    }
}
