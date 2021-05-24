package com.myself.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Case56Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        //按照区间的左端点排序
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < intervals.length; ++i) {
            //取出每个区间的左右端点
            int L = intervals[i][0], R = intervals[i][1];
            if (res.size() == 0 || res.get(res.size() - 1)[1] < L) {
                //res中最后一个区间的右端点比当前左区间还小，说明两个区间存在断档，应该新增进去
                res.add(new int[]{L, R});
            } else {
                //res中最后一个区间的右端点>=当前左区间,说明有重叠，应该更新res中最后一个区间的右端点，以扩大区间长度
                res.get(res.size() - 1)[1] = Math.max(res.get(res.size() - 1)[1], R);
                //注意这种写法，List中存的是数组，res.get(res.size() - 1)取出的是一个数组，再跟【1】取到右端点
            }
        }
        return res.toArray(new int[res.size()][]);
    }

    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] merge = new Case56Solution().merge(intervals);
        System.out.println(Arrays.deepToString(merge));

    }
}
