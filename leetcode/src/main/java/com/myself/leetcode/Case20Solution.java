package com.myself.leetcode;

import java.util.Stack;

public class Case20Solution {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ('(' == c || '{' == c || '[' == c) {
                stack.push(c);
            } else {
                if (!stack.isEmpty() && getNeedLeftChar(c) == stack.peek()) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    char getNeedLeftChar(char c) {
        if (']' == c) {
            return '[';
        }
        if ('}' == c) {
            return '{';
        }
        return '(';
    }
}
