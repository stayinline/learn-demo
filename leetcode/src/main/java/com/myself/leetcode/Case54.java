package com.myself.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Case54 {

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ret = new ArrayList<>();
        int m = matrix.length; //行数
        int n = matrix[0].length; //列数

        int left = 0, right = n - 1;
        int up = 0, down = m - 1;
        while (true) {

            //向右遍历
            for (int i = left; i <= right; i++) {
                ret.add(matrix[up][i]);
            }
            up++;
            if (up > down) break;

            // 向下遍历
            for (int i = up; i <= down; i++) {
                ret.add(matrix[i][right]);
            }
            right--;
            if (left > right) break;


            // 向左遍历
            for (int i = right; i >= left; i--) {
                ret.add(matrix[down][i]);
            }
            down--;
            if (down < up) break;

            // 向上遍历
            for (int i = down; i >= up; i--) {
                ret.add(matrix[i][left]);
            }
            left++;
            if (left > right) break;

        }
        return ret;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        int m = matrix.length;
        int n = matrix[0].length;

        System.out.println(m);
        System.out.println(n);


        List<Integer> list = spiralOrder(matrix);
        System.out.println(list);
    }
}
