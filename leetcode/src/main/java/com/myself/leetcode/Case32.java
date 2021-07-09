package com.myself.leetcode;

import java.util.Stack;

public class Case32 {


    // 动态规划
    public int longestValidParenthesesDP(String s) {
        int ret = 0;
        // dp[i] 表示以下标 i 字符结尾的最长有效括号的长度
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            // 遇到 ) 才需要往前校验，( 不用处理
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                ret = Math.max(ret, dp[i]);
            }
        }
        return ret;
    }


    // 两次遍历法：
    public int longestValidParentheses(String s) {
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right > left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return maxlength;
    }


    // 暴力法：过不了！！！
    public int longestValidParentheses1(String s) {
        int ret = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                if (isValid(s.substring(i, j + 1))) {
                    ret = Math.max(ret, j - i + 1);
                }
            }
        }
        return ret;

    }


    // 不能只用左右括号的出现次数是否相等判断，如：))((  是不合法的
    private boolean isValid(String str) {
        if (str.charAt(0) != '(') {
            return false;
        }
        int l = 0, r = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if ('(' == c) {
                l++;
            } else if (')' == c) {
                l--;
                if (l < 0) {
                    return false;
                }
            }
        }
        return l == 0;
    }


    public static void main(String[] args) {
        String str = ")()())()()(";
        int ret = new Case32().longestValidParentheses(str);
        System.out.println(ret);
    }


}
