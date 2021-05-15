package com.myself.leetcode.case46;

import java.util.*;

public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> track = new LinkedList<>();
        backTrack(nums, track, res);
        return res;
    }

    private void backTrack(int[] nums, LinkedList<Integer> track, List<List<Integer>> res) {
        //这里当数组长度和路径track的长度相等时，退出递归
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            //判断路径是否包含当前元素，包含说明已经采用过了，不包含时则可以采用
            if (track.contains(nums[i])) {
                continue;
            }

            track.add(nums[i]);

            //递归从头开始寻找可选队列中的元素
            backTrack(nums, track, res);

            //出递归的时候，一定是上一层中已经选取过当前元素，所以退回到上一层的时候需要把决策树的最后一层去掉
            track.removeLast();
        }

    }

    public int longestPalindrome2(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 1));
        }
        int max = 0;
        int sum = 0;
        for (Character c : map.keySet()) {
            Integer count = map.get(c);
            if (count % 2 == 1) {
                max = max > count ? max : count;
            } else {
                sum += count;
            }
        }
        return max + sum;
    }

    public int longestPalindrome(String s) {
        int[] count = new int[128];
        int length = s.length();
        for (int i = 0; i < length; ++i) {
            char c = s.charAt(i);
            count[c]++;
        }

        int ans = 0;
        for (int v : count) {
            ans += v / 2 * 2;
            if (v % 2 == 1 && ans % 2 == 0) {
                ans++;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
//        int[] nums = {1, 2, 3};
//        List<List<Integer>> permute = new Solution().permute(nums);
        String s = "abcccdd";
        int i = new Solution().longestPalindrome(s);
    }
}