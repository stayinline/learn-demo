package com.myself.leetcode;

import java.util.ArrayList;
import java.util.Arrays;

public class Case31 {


    public static void nextPermutation(int[] nums) {
        int a = nums.length - 2;
        // a 找第一个非递减的
        while (a >= 0 && nums[a] >= nums[a + 1]) {
            a--;
        }
        if (a >= 0) {
            int j = nums.length - 1;
            // 找到第一个比nums[a]大的，但是是最小的元素
            while (j >= 0 && nums[a] >= nums[j]) {
                j--;
            }
            // 交换
            swap(nums, a, j);
        }
        // 翻转a后面的元素
        reverse(nums, a + 1, nums.length - 1);
    }


    private static void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    private static void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 这种方法存在问题，如：[1,3,2],期望：[2,1,3],结果：[3,1,2]
     *
     * @param nums
     */
    public void nextPermutation1(int[] nums) {
        int n = nums.length;
        for (int i = n - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                swap(nums, i, i - 1);
                return;
            }
        }
        Arrays.sort(nums);
    }

}
