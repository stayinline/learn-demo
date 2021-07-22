package com.myself.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Case395 {

    public static int longestSubstring(String s, int k) {
        if (s == null) {
            return 0;
        }
        int ret = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                boolean valid = isValidArr(s, i, j, k);
                ret = valid ? Math.max(ret, j - i) : ret;
            }
        }
        return ret;
    }

    public static boolean isValid(String s, int start, int end, int k) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = start; i < end; i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (Integer count : map.values()) {
            if (count < k) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidArr(String s, int start, int end, int k) {
        int[] countArr = new int[26];
        for (int i = start; i < end; i++) {
            countArr[s.charAt(i) - 'a']++;
        }
        for (int count : countArr) {
            if (count < k && count != 0) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        String s = "aaabb";
//        System.out.println(s.substring(0,5));
        System.out.println(longestSubstring(s, 3));
    }
}
