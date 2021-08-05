package com.myself.leetcode;

public class Case334 {


    // 暴力法
    public boolean increasingTriplet1(int[] nums) {
        int n = nums.length;
        if (n < 1) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            int numi = nums[i];
            for (int j = i + 1; j < n; j++) {
                int numj = nums[j];
                if (numi < numj) {
                    for (int k = j + 1; k < n; k++) {
                        if (numj < nums[k]) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return false;
        }
        int a = Integer.MAX_VALUE, b = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            if (a >= nums[i]) {
                a = nums[i];
            } else if (b >= nums[i]) {
                b = nums[i];
            } else if (nums[i] > b) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 2147483647};
        boolean b = increasingTriplet(arr);
        System.out.println(b);
    }


}
