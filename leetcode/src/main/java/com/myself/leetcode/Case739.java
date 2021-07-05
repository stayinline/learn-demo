package com.myself.leetcode;

import java.util.Arrays;

/**
 * temperatures = [73, 74, 75, 71, 69, 72, 76, 73]
 * result=        [1,  1,   4,  2,  1,  1,  0,  0]
 */
public class Case739 {
    public static int[] dailyTemperatures(int[] t) {
        int len = t.length;
        int[] ret = new int[len];
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (t[i] < t[j]) {
                    ret[i] = j - i;
                    break;
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] t = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] ints = dailyTemperatures(t);
        System.out.println(Arrays.toString(ints));
    }
}
