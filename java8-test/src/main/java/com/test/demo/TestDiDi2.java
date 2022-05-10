package com.test.demo;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class TestDiDi2 {

    // 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度
    // 输入: s = "abcabcbb".abbcdb
    // 输出: 3
    // 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3

    public static int maxLen(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int len = 0;
        Set<Character> set = new HashSet<>();
        set.add(s.charAt(0));
        for (int i = 0, j = 1; j < s.length(); j++) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j));
            } else {
                len = Math.max(len, j - i);
                char c = s.charAt(i);
                while (set.contains(c) && i < s.length()) {
                    set.remove(c);
                    i++;
                }
            }
        }
        return len;
    }

    public static int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int i = 0, j = 0, max = 0;
        while (i < s.length()) {
            if (i != 0) {
                set.remove(s.charAt(i - 1));
            }

            while (j < s.length() && !set.contains(s.charAt(j))) {
                set.add(s.charAt(j));
                j++;
            }
            max = Math.max(max, j - i);
            i++;
        }
        return max;
    }


    //给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
    //
    //有效字符串需满足：
    //
    //1.左括号必须用相同类型的右括号闭合。
    //2.左括号必须以正确的顺序闭合。

    // 输入：s = "()"
    //输出：true
    //
    //输入：s = "()[]{}"
    //输出：true
    //
    //输入：s = "([)]"
    //输出：false
    //
    //输入：s = "{[]}"
    //输出：true

    // {[}]




    // {[]} -> true
    public static boolean check2(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }

        Stack<Character> stack = new Stack<>();
        stack.push(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            if (!stack.isEmpty() && pipei(stack.peek(), s.charAt(i))) {
                stack.pop();
            } else {
                stack.push(s.charAt(i));
            }
        }

        return stack.isEmpty();
    }

    public static boolean pipei(Character a, Character b) {
        if ('{' == a && '}' == b) {
            return true;
        } else if ('[' == a && ']' == b) {
            return true;
        } else if ('(' == a && ')' == b) {
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        String s = "abbcdb";
        System.out.println(maxLen(s));
        System.out.println(lengthOfLongestSubstring(s));

//        String s = "([)]";
//        System.out.println(check2(s));
    }


}
