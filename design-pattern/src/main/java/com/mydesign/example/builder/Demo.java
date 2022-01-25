package com.mydesign.example.builder;

import lombok.Builder;

public class Demo {

    public static void main(String[] args) {
        // User user = new User();

        User2 user2 = User2.builder()
                .address("北京")
                .age(11)
                .build();
    }


    private static class User {
        private String name;
        private String nickName;
        private String address;
        private int age;
        private double weight;
        private double high;


        /**
         * 问题一：
         * 假设字段很多，并且每个字段不一定会全都需要，那么如何设计构造函数？
         */
    }

    /**
     * 使用lombok的注解实现一个builder模式
     */
    @Builder
    private static class User2 {
        private String name;
        private String nickName;
        private String address;
        private int age;
        private double weight;
        private double high;
    }

}
