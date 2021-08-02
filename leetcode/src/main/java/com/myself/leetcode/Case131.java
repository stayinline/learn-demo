package com.myself.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Case131 {
    public List<List<String>> partition(String s) {
        List<List<String>> ret = new ArrayList<>();


        return ret;
    }

    public static boolean isPalindrome(String s) {
        int n = s.length();
        int left = 0, right = n - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                ++left;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                --right;
            }
            if (left < right) {
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                    return false;
                }
                ++left;
                --right;
            }
        }
        return true;
    }

    class Solution {
        boolean[][] flags; // 记录
        List<List<String>> ret = new ArrayList<>();
        LinkedList<String> ans = new LinkedList<>();
        int n;

        public List<List<String>> partition(String s) {
            n = s.length();
            flags = new boolean[n][n];
            for (int i = 0; i < n; ++i) {
                Arrays.fill(flags[i], true);
            }

            for (int i = n - 1; i >= 0; --i) {
                for (int j = i + 1; j < n; ++j) {
                    flags[i][j] = (s.charAt(i) == s.charAt(j)) && flags[i + 1][j - 1];
                }
            }

            backTrack(s, 0);
            return ret;
        }

        //回溯法
        public void backTrack(String s, int index) {
            if (index == n) {
                ret.add(new ArrayList<>(ans));
                return;
            }
            for (int j = index; j < n; ++j) {
                if (flags[index][j]) {
                    ans.add(s.substring(index, j + 1));
                    backTrack(s, j + 1);
                    ans.removeLast();
                }
            }
        }
    }

}
