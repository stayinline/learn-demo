package com.java8.test.fork.demo2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.function.Function;

public class ForkListService<T, R> extends RecursiveTask<List<R>> {
    private int size = 2;
    private int start;
    private int end;

    private List<T> inputList;

    private Function<List<T>, List<R>> function;


    public ForkListService(int size) {
        this.size = size;
    }

    public ForkListService(List<T> inputList, int start, int end, Function<List<T>, List<R>> function) {
        this.inputList = inputList;
        this.start = start;
        this.end = end;
        this.function = function;
    }


    @Override
    protected List<R> compute() {
        if (inputList.size() <= size) {
            return function.apply(inputList);
        } else {
            int middle = inputList.size() / 2;
            int length = inputList.size();
            List<T> leftHalf = inputList.subList(0, middle);
            List<T> rightHalf = inputList.subList(middle, inputList.size());

            ForkListService<T, R> leftTask = new ForkListService<>(leftHalf, start, start + length / 2, function);
            ForkListService<T, R> rightTask = new ForkListService<>(rightHalf, start, start + length / 2, function);

            leftTask.fork();
            rightTask.fork();

            List<R> leftResult = leftTask.join();
            List<R> rightResult = rightTask.join();

            List<R> result = new ArrayList<>(leftResult);
            result.addAll(rightResult);

            return result;
        }
    }

}



