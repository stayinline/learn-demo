package com.myself.leetcode;

public class Case647 {
    class Solution {
        public int countSubstrings(String s) {
            int n = s.length(), ans = 0;
            // 枚举每一个可能是回文串的中心，然后向两边拓展，
            // 长度为 n 的字符串会生成 2n−1 组回文中心
            for (int i = 0; i < 2 * n - 1; ++i) {
                int l = i / 2, r = i / 2 + i % 2;
                while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                    --l;
                    ++r;
                    ++ans;
                }
            }
            return ans;
        }
    }
}
