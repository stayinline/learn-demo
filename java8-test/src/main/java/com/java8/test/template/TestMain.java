package com.java8.test.template;

import java.io.Serializable;
import java.util.List;

public class TestMain {

    public static void main(String[] args) {
        Pair<String> stringPair = new Pair<>("hello", "word");
        Object firstObj = stringPair.getFirst();
        String first = (String) firstObj;

        Pair<Integer> intPair = new Pair<>(1, 11);

        System.out.println(stringPair.getClass() == intPair.getClass());

//        Pair<? extends Person> pair = new Pair<>();
//        Pair<? super Person> pair = new Pair<>();
//        Pair<?> pair = new Pair<>();
    }

    public static class Pair<T> {
        private T first;
        private T second;

        public Pair(T first, T second) {
            this.first = first;
            this.second = second;
        }

        public T getFirst() {
            return first;
        }

        public void setFirst(T first) {
            this.first = first;
        }

        public T getSecond() {
            return second;
        }

        public void setSecond(T second) {
            this.second = second;
        }
    }

    public static class Test {

        public static <T> int getLength(T[] b) {
            return b.length;
        }

        // 没有<T> 就编译不过
//        public static int getLength(T[] b) {
//            return b.length;
//        }

    }

    public static class TestRange<T> {
        // 指定泛型的多个特定接口
        public static <T extends Comparable & List> int getLength(T[] b) {
            // 这里就可以直接用指定接口的功能，当然，调用方需要遵守这个约定
            System.out.println(b[0].isEmpty());
            return b.length;
        }
    }

//    public static class TestRange2<Comparable> {
//        public static <Comparable> int getLength(Comparable[] b) {
//            // 这里没有了List，就一定会报错，怎么办呢
//            System.out.println(b[0].isEmpty());
//            return b.length;
//        }
//
//        public static <Comparable> int getLength(Comparable[] b) {
//            // 这里没有了List，就一定会报错，怎么办呢
//            System.out.println(b[0].isEmpty());
//            return b.length;
//        }
//    }
//
//    public static class TestRange3<Pair<T>>
//
//    {
//        public static <Pair<T>>int getLength (T[]b){
//        // 这里没有了List，就一定会报错，怎么办呢
//        System.out.println(b[0].isEmpty());
//        return b.length;
//    }
//    }

//    public static class Singleton<T> {
//        private static T INSTANCE;
//
//        private Singleton() {
//        }
//
//        public static T getInstance() {
//            if (null == INSTANCE) {
//                return new Singleton<>();
//            }
//        }
//    }

    public static class Person {


    }


}
