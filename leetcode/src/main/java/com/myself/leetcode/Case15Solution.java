package com.myself.leetcode;

import org.assertj.core.util.Lists;

import java.lang.reflect.Array;
import java.util.*;

public class Case15Solution {

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> two = twoSum(nums, i + 1, 0 - nums[i], false);
            for (List<Integer> tuple : two) {
                tuple.add(nums[i]);
                res.add(tuple);
            }
            // 跳过第一个数字重复的情况，否则会出现重复结果
            while (i < nums.length - 1 && nums[i] == nums[i + 1]) i++;

        }
        return res;
    }


    public List<List<Integer>> twoSum(int[] nums, int start, int target, boolean needSort) {
        if (needSort) {
            Arrays.sort(nums);
        }
        int l = start, r = nums.length - 1;
        List<List<Integer>> res = new ArrayList<>();
        while (l < r) {
            int left = nums[l];
            int right = nums[r];
            int tmpSum = left + right;
            if (tmpSum < target) {
                //去掉重复元素
                while (l < r && nums[l] == left) l++;
            } else if (tmpSum > target) {
                while (l < r && nums[r] == right) r--;

            } else {
                res.add(new ArrayList<Integer>() {{
                    add(left);
                    add(right);
                }});
                while (l < r && nums[l] == left) l++;
                while (l < r && nums[r] == right) r--;
            }
        }
        return res;
    }

    public int missingNumber(int[] nums) {
        int n = nums.length;
        int res = 0;
        res ^= n;
        for (int i = 0; i < n; i++) {
            res ^= i ^ nums[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {0, 0, 0};
        int[] nums2 = {-1, 2, 1, 0, -4, -1};
        StopWatchUtils.start();
        List<List<Integer>> lists = new Case15Solution().threeSum(nums);
        System.out.println(lists + "time:" + StopWatchUtils.stopAndGetTimeMillis());

        List<List<Integer>> lists2 = new Case15Solution().threeSum(nums2);
        System.out.println(lists2);
    }
}
