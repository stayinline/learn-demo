package com.java8.test.functional;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.function.Consumer;

public class ConsumerDemo {

    public static void main(String[] args) {
        testConsumer1();

        testConsumer2();


    }

    private static void testConsumer1() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Random().nextInt(20));
        }
        System.out.println("原始数组：" + list);
        foreach1(list, (Integer i) -> i *= 2);
        System.out.println("*=2之后：" + list);
        /* 输出
         * 原始数组：[7, 8, 8, 5, 5, 14, 0, 10, 5, 18]
         * *=2的之后：[7, 8, 8, 5, 5, 14, 0, 10, 5, 18]
         */

        //为何会不生效？
    }

    private static void foreach1(List<Integer> list, Consumer<Integer> c) {
        for (Integer item : list) {
            c.accept(item);
        }
    }


    /**
     * 这种是不合乎函数式编程的规范的
     */
    private static void testConsumer2() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            User user = new User();
            user.setName(i + "---");
            user.setId(i);
            list.add(user);
        }
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Bob");
        System.out.println("原始数组：" + list);
        foreach2(list, (User user) -> user.name += "__test", map);
        System.out.println("foreach2之后：" + list);

    }


    private static void foreach2(List<User> list, Consumer<User> c, Map<Integer, String> map) {
        for (User item : list) {
            c.accept(item);
            item.setEnglishName(map.getOrDefault(item.getId(), "default"));
        }
    }



    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static final class User {
        private Integer id;
        private String name;
        private String englishName;

    }
}
