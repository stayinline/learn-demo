package com.myself.leetcode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Case76 {

    // t字符串中每个字符出现的次数
    Map<Character, Integer> tMap = new HashMap<>();
    // 滑动窗口中每个字符及其出现次数
    Map<Character, Integer> windowMap = new HashMap<>();

    public String minWindow(String s, String t) {
        // 边界值判断
        if (s == null || s.length() == 0 || t == null || t.length() == 0) {
            return "";
        }
        int tLen = t.length();
        int sLen = s.length();

        //将t全部存入map
        for (int i = 0; i < tLen; i++) {
            char c = t.charAt(i);
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }

        //定义滑动窗口的左右指针
        int l = 0, r = -1;
        int len = Integer.MAX_VALUE, ansL = -1, ansR = -1;
        while (r < sLen) {
            ++r;
            if (r < sLen && tMap.containsKey(s.charAt(r))) {
                windowMap.put(s.charAt(r), windowMap.getOrDefault(s.charAt(r), 0) + 1);
            }
            while (check() && l <= r) {
                //满足条件则尝试缩小滑动窗口的左指针
                if (r - l + 1 < len) {
                    len = r - l + 1;
                    ansL = l;
                    ansR = l + len;
                }
                if (tMap.containsKey(s.charAt(l))) {
                    windowMap.put(s.charAt(l), windowMap.getOrDefault(s.charAt(l), 0) - 1);
                }
                ++l;
            }
            // 当前滑动窗口不满足之后继续循环，窗口右移
        }
        return ansL == -1 ? "" : s.substring(ansL, ansR);
    }

    // 判断 windowMap 是否包含全部的 tMap
    public boolean check() {
        Iterator iter = tMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Character key = (Character) entry.getKey();
            Integer val = (Integer) entry.getValue();
            if (windowMap.getOrDefault(key, 0) < val) {
                return false;
            }
        }
        return true;
    }

}
