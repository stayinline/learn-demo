package com.myself.leetcode;

import org.assertj.core.util.Lists;

import java.util.*;

public class Case140 {

    private static List<String> result = new ArrayList<>();


//    public static List<String> wordBreak(String s, List<String> wordDict) {
//
//        List<String> result = new ArrayList<>();
//        if (s.length() == 0) {
//            return result;
//        }
//
//        back(s, wordDict, 0, 1, new LinkedList<>());
//
//        return result;
//
//    }
//
//    public static void back(String s, List<String> wordDict, int start, int end, LinkedList<String> splitWord) {
//        if (end == s.length()) {
//            result.add(s.substring(start, end));
//            return;
//        }
//
//        for (int i = 0; i < s.length(); ) {
//            for (int j = i + 1; j < s.length(); ) {
//                String substr = s.substring(i, j);
//                if (!wordDict.contains(substr)) {
//                    j++;
//                }
//                splitWord.add(substr);
//
//                back(s, wordDict, i, j, splitWord);
//
//                splitWord.removeLast();
//            }
//
//        }
//    }


    static class Solution {
        public List<String> wordBreak(String s, List<String> wordDict) {
            // k:遍历到s中的下标，v:当前下标中能切分出来的单词的结果集，备忘录思想，不用每次都计算
            Map<Integer, List<List<String>>> map = new HashMap<>(s.length());
            List<List<String>> wordBreaks = backtrack(s, new HashSet<>(wordDict), 0, map);
            List<String> breakList = new LinkedList<>();
            for (List<String> wordBreak : wordBreaks) {
                breakList.add(String.join(" ", wordBreak));
            }
            return breakList;
        }

        /**
         * @param s
         * @param wordSet set表示单词字典不会重复
         * @param index   遍历到的下标
         * @param map     记录遍历过程中切分出来的单词
         * @return
         */
        public List<List<String>> backtrack(String s, Set<String> wordSet, int index,
                                            Map<Integer, List<List<String>>> map) {
            if (!map.containsKey(index)) {
                List<List<String>> splitWords = new LinkedList<>();
                int length = s.length();

                // 回溯法框架：
                if (index == length) {
                    splitWords.add(new LinkedList<>());
                }
                for (int i = index + 1; i <= length; i++) {
                    String word = s.substring(index, i);
                    //只处理当前包含的
                    if (wordSet.contains(word)) {
                        List<List<String>> nextWordBreaks = backtrack(s, wordSet, i, map);
                        for (List<String> nextWordBreak : nextWordBreaks) {
                            LinkedList<String> wordBreak = new LinkedList<>(nextWordBreak);
                            wordBreak.offerFirst(word);
                            splitWords.add(wordBreak);
                        }
                    }
                }
                map.put(index, splitWords);
            }
            return map.get(index);
        }
    }

    public static void main(String[] args) {
        String s="catsanddog";

        ArrayList<String> wordDicts = Lists.newArrayList("cat", "cats", "and", "sand", "dog");

        List<String> ret = new Solution().wordBreak(s, wordDicts);


    }

}
