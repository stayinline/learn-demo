package com.myself.leetcode;

public class Case289 {

    public void gameOfLife(int[][] board) {
        // 0:当前行；
        // 1：下一行；
        // -1：上一行
        int[] neighbors = {0, 1, -1};

        int rows = board.length; // 行
        int cols = board[0].length; // 列

        // 创建复制数组 copyBoard
        int[][] copyBoard = new int[rows][cols];

        // 从原数组复制一份到 copyBoard 中
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                copyBoard[row][col] = board[row][col];
            }
        }

        // 遍历面板每一个格子里的细胞
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                // 对于每一个细胞统计其八个相邻位置里的活细胞数量
                int liveNeighbors = 0;

                // 双层遍历，统计八个位置；写八个if也可
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {

                        // 边界判断
                        if (!(neighbors[i] == 0 && neighbors[j] == 0)) {
                            int r = (row + neighbors[i]);
                            int c = (col + neighbors[j]);

                            // 查看相邻的细胞是否是活细胞
                            if ((r < rows && r >= 0)  // 行边界判断
                                    && (c < cols && c >= 0) // 列边界判断
                                    && (copyBoard[r][c] == 1)) { // 细胞存活判断
                                liveNeighbors += 1;
                            }
                        }
                    }
                }

                // 规则 1 或规则 3
                if ((copyBoard[row][col] == 1) && (liveNeighbors < 2 || liveNeighbors > 3)) {
                    board[row][col] = 0;
                }
                // 规则 4
                if (copyBoard[row][col] == 0 && liveNeighbors == 3) {
                    board[row][col] = 1;
                }
            }
        }
    }

}
