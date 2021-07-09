//给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。 
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。 
//
// 
//
// 示例 1： 
//
// 
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "AB
//CCED"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SE
//E"
//输出：true
// 
//
// 示例 3： 
//
// 
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "AB
//CB"
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// m == board.length 
// n = board[i].length 
// 1 <= m, n <= 6 
// 1 <= word.length <= 15 
// board 和 word 仅由大小写英文字母组成 
// 
//
// 
//
// 进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？ 
// Related Topics 数组 回溯 矩阵 
// 👍 947 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
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
                    boolean curRet = checkArray(board, i, j, visit, word, idxOfWord + 1, hlen, wlen);
                    if (curRet) {
                        ret = true;
                        break;
                    }
                }

            }
        }
        // 回溯法之撤销上一步的操作
        visit[i][j] = false;
        return false;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
