package com.myself.leetcode.case7;


import com.myself.leetcode.StopWatchUtils;

/**
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * <p>
 * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
 * <p>
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 */
class Solution {
    public int reverse(int x) {
        int result = 0;
        while (x != 0) {
            int temp = x % 10;
            int tempResult = result * 10 + temp;
            //判断是否有溢出:溢出之后这个等式不成立
            if ((tempResult - temp) / 10 != result) {
                return 0;
            }
            result = tempResult;
            x /= 10;
        }
        return result;
    }


    public static void main(String[] args) {
        StopWatchUtils.start();
        int reverse = new Solution().reverse(1234);
        StopWatchUtils.stopAndGetTimeMillis();
        System.out.println(reverse);
    }
}