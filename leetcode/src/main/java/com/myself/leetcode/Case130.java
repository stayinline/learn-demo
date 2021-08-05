package com.myself.leetcode;

import java.util.Arrays;

public class Case130 {

    private static int m, n;


    public static void solve(char[][] board) {
        m = board.length;
        n = board[0].length;


        for (int i = 0; i < m; i++) {
            dfs(board, i, 0); // 最左边一列的每一行
            dfs(board, i, n - 1); // 最右边一列的每一行
        }

        for (int i = 0; i < n - 1; i++) {
            dfs(board, 0, i); // 最上边一行的每一列
            dfs(board, m - 1, i); // 最下边一行的每一列
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'a') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    // x:遍历行数，y:遍历列数，将board中没有被围绕的记录成'a'
    public static void dfs(char[][] board, int x, int y) {
        if (x < 0 || x >= m || y < 0 || y >= n || board[x][y] != 'O') {
            return;
        }
        board[x][y] = 'a'; // 这里说明不是被围绕的，记录成'a'
        dfs(board, x + 1, y); // 下
        dfs(board, x - 1, y); // 上
        dfs(board, x, y + 1); // 右
        dfs(board, x, y - 1); // 左

    }

    public static void main(String[] args) {
        char[][] board = {
                {'X', 'O', 'X', 'O', 'X', 'O'},
                {'O', 'X', 'O', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'O', 'X', 'O'},
                {'O', 'X', 'O', 'X', 'O', 'X'}};

        solve(board);
        System.out.println(Arrays.deepToString(board));
    }


}
