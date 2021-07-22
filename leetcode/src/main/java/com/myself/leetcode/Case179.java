package com.myself.leetcode;


import java.util.*;

public class Case179 {


    public static String largestNumber(int[] nums) {
        if (nums.length == 0) {
            return "";
        }
        ArrayList<Integer> list = new ArrayList<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            list.add(nums[i]);
        }
        list.sort((x, y) -> compareTo(x, y));
        if (list.get(0) == 0) {
            return "0";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer num : list) {
            stringBuilder.append(num);
        }
        return stringBuilder.toString();
    }

    /**
     * 此方法覆盖的测试用例不全，不能AC ！！！
     * 从最高位开始比较x y的大小
     * -1：x>y；
     * 1：x<y；
     * 0：x=y
     */
    public static int getMax(int x, int y) {
        ArrayList<Integer> xs = split(x);
        ArrayList<Integer> ys = split(y);
        int i = xs.size() - 1, j = ys.size() - 1;
        while (i >= 0 && j >= 0) {
            if (xs.get(i) > ys.get(j)) {
                return -1;
            } else if (xs.get(i) < ys.get(j)) {
                return 1;
            }
            i--;
            j--;
        }
        if (i >= 0) {
            return -1;
        }
        if (j >= 0) {
            return 1;
        }
        return 0;
    }

    public static int compareTo(int x, int y) {
        long sx = 10, sy = 10;
        while (sx <= x) {
            sx *= 10;
        }
        while (sy <= y) {
            sy *= 10;
        }
        return (int) (-sy * x - y + sx * y + x);
    }

    private static ArrayList<Integer> split(int a) {
        ArrayList<Integer> as = new ArrayList<>();
        while (a % 10 != 0) {
            as.add(a % 10);
            a /= 10;
        }
        return as;
    }

    public static int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }

    public static boolean isHappy(int n) {
        int slowRunner = n;
        int fastRunner = getNext(n);
        while (fastRunner != 1 && slowRunner != fastRunner) {
            slowRunner = getNext(slowRunner);
            fastRunner = getNext(getNext(fastRunner));
        }
        return fastRunner == 1;
    }

    public static void main(String[] args) {

        System.out.println(isHappy(2));
    }
}
