package com.myself.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Case17 {
    private static Map<Character, String> phoneMap =
            new HashMap<Character, String>() {{
                put('2', "abc");
                put('3', "def");
                put('4', "ghi");
                put('5', "jkl");
                put('6', "mno");
                put('7', "pqrs");
                put('8', "tuv");
                put('9', "wxyz");
            }};

    public List<String> letterCombinations(String digits) {
        List<String> results = new ArrayList<>();
        if (digits.length() == 0) {
            return results;
        }
        backtrack(results, digits, 0, new StringBuffer());
        return results;
    }

    // 经典回溯法
    public void backtrack(List<String> results, String digits, int index, StringBuffer cur) {
        if (index == digits.length()) {
            //满足条件，加入结果集
            results.add(cur.toString());
        } else {
            char curDigit = digits.charAt(index);
            String letters = phoneMap.get(curDigit);
            int lettersCount = letters.length();

            // 遍历可选项，逐个判断
            for (int i = 0; i < lettersCount; i++) {
                // 当前值加入
                cur.append(letters.charAt(i));

                // 通过递归调用，判断下一个可选项，所以 index+1
                backtrack(results, digits, index + 1, cur);

                // 遍历完，撤销上一次选择
                cur.deleteCharAt(index);
            }
        }
    }

    public static void main(String[] args) {
        List<String> strings = new Case17().letterCombinations("23");
        System.out.println(strings);
    }
}
