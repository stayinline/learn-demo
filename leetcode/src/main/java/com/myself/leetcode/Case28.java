package com.myself.leetcode;

public class Case28 {

    public static int strStr(String haystack, String needle) {
        int n = haystack.length(), m = needle.length();
        for (int i = 0; i + m <= n; i++) {
            boolean flag = true;
            for (int j = 0; j < m; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        String a = "mississippi";

        String b = "issipi";

        int index = strStr(a, b);

        System.out.println(index);
    }
}
