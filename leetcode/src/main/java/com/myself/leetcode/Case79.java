package com.myself.leetcode;

public class Case79 {
    public boolean exist(char[][] board, String word) {
        int hlen = board.length;
        if (hlen == 0) {
            return false;
        }
        int wlen = board[0].length;
        boolean[][] visit = new boolean[hlen][wlen];
        for (int i = 0; i < hlen; i++) {
            for (int j = 0; j < wlen; j++) {
                boolean ret = checkArray(board, i, j, visit, word, 0, hlen, wlen);
                if (ret) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkArray(char[][] board, int i, int j, boolean[][] visit, String word, int idxOfWord, int hlen, int wlen) {
        if (board[i][j] != word.charAt(idxOfWord)) {
            // 当前不等，一定是不存在
            return false;
        } else if (idxOfWord == word.length() - 1) {
            // word结束，一定是存在
            return true;
        }// 越界问题不用考虑，上一个调用已经处理了

        visit[i][j] = true;
        boolean ret = false;

        // 上下左右四个坐标
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] dir : directions) {
            int curi = i + dir[0], curj = j + dir[1];
            if (curi >= 0 && curi < hlen && curj >= 0 && curj < wlen) {
                if (!visit[curi][curj]) {
                    boolean curRet = checkArray(board, curi, curj, visit, word, idxOfWord + 1, hlen, wlen);
                    if (curRet) {
                        ret = true;
                        break;
                    }
                }

            }
        }
        // 回溯法之撤销上一步的操作
        visit[i][j] = false;
        return ret;
    }

    public static void main(String[] args) {
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";
        boolean exist = new Case79().exist(board, word);
        System.out.println(exist);
    }

}
