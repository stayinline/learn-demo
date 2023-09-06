package com.java8.test.fork.demo2;

public class ForkListWrapper<T, R> {

    public static R forkExecute(List<T> param) {
        return new ForkListService<>(param, 0, param.size()).compute();
    }
}
