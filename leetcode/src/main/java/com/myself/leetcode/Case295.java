package com.myself.leetcode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Case295 {

    public static class MedianFinder {

        private ArrayList<Integer> values;

        public MedianFinder() {
            values = new ArrayList<>();
        }

        public void addNum(int num) {
            values.add(num);
            Collections.sort(values);
        }

        public double findMedian() {
            int j = values.size();
            if (values.size() % 2 == 0) {
                return new Integer(values.get(j / 2) + values.get(j / 2 - 1)).doubleValue() / 2;
            } else {
                return values.get(j / 2);
            }
        }


    }

    public static void test() {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        double median = medianFinder.findMedian();
        System.out.println(median);


        medianFinder.addNum(3);

        System.out.println(medianFinder.findMedian());

    }

    public static void main(String[] args) {
        test();

    }
}
