package com.myself.leetcode;

import java.util.Arrays;

public class Case73 {


    public static void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] rows = new int[m];
        int[] cols = new int[n];

        for (int i = 0; i < m; i++) {
            for (int j = i; j < n; j++) {
                if (matrix[i][j] == 0 && rows[i] == 0 && cols[j] == 0) {
                    rows[i] = 1;
                    cols[j] = 1;
                    for (int a = 0; a < m; a++) {
                        matrix[a][j] = 0;
                    }
                    for (int a = 0; a < n; a++) {
                        matrix[i][a] = 0;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        int[][] arr = {
                {0, 1, 2, 0},
                {3, 4, 5, 2},
                {1, 3, 1, 5}};
        setZeroes(arr);
        System.out.println(Arrays.deepToString(arr));
    }
}
