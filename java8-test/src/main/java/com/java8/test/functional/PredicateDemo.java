package com.java8.test.functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateDemo {


    public static void main(String[] args) {
        //testPredicate1();
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

        List<String> names = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp", "Hell", "opt");
        //长度为7
        Predicate<String> length = (n) -> n.length() == 4;
        // endsWith 方法字符串是否以指定的前缀开头。
        Predicate<String> startsWith = (n) -> n.startsWith("J");
        // endsWith 字符串是否以指定的后缀结尾。
        Predicate<String> endsWith = (n) -> n.endsWith("a");
        Predicate<String> isEqual = (n) -> Predicate.isEqual("Haskell").test(n);
        names.stream()
                .filter(length.and(startsWith).and(endsWith).or(isEqual))
                .forEach((n) -> System.out.println("this is:" + n));

        System.out.println(names);

        System.out.println(names.stream()
                .sorted((s1, s2) -> s1.compareTo(s2))
                .collect(Collectors.toList()));

        List<String> sortList = names.stream()
                .sorted((s1, s2) -> s1.compareTo(s2))
                .sorted((s1, s2) -> s1.length() < s2.length() ? 1 : -1)
                .collect(Collectors.toList());

        System.out.println(sortList);

    }
}
