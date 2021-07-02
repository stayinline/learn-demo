package com.myself.leetcode;

public class Case55 {
    public static boolean canJump(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return false;
        }
        int maxIdx = 0;
        for (int i = 0; i < n; i++) {
            // maxIdx如果已经覆盖了i，那么没有必要再处理当前位置的元素
            if (i <= maxIdx) {
                // i + nums[i]是当前位置能到的最远下标，对比更新取最大的
                maxIdx = Math.max(maxIdx, i + nums[i]);
                // n是数组长度，n-1是最后一个元素的下标，所以是 maxIdx >= n - 1即可
                if (maxIdx >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 0, 4};
        boolean b = canJump(nums);
        System.out.println(b);
    }
}
