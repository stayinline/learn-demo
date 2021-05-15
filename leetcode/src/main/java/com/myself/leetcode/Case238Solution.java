package com.myself.leetcode;

/**
 * @author hemaoling
 */
public class Case238Solution {

    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] ret = new int[len];

        int[] left = new int[len];
        left[0] = 1;
        //记录nums[i]左边的所有数字的乘积
        for (int i = 1; i < len; i++) {
            left[i] = nums[i - 1] * left[i - 1];
        }
        int[] right = new int[len];
        right[len - 1] = 1;
        //记录nums[i]右边的所有数字的乘积
        for (int i = len - 2; i >= 0; i--) {
            right[i] = nums[i + 1] * right[i + 1];
        }

        //nums[i] = 左 * 右
        for (int i = 0; i < len; i++) {
            ret[i] = left[i] * right[i];
        }
        return ret;
    }

    public int[] productExceptSelf2(int[] nums) {
        int len = nums.length;
        int[] ret = new int[len];
        ret[0] = 1;
        //用ret数组记录nums[i]左边的所有数字的乘积
        for (int i = 1; i < len; i++) {
            ret[i] = nums[i - 1] * ret[i - 1];
        }

        //nums[i]右边的乘积不用全部记录，只需要从最右边开始记录，然后迭代更新即可
        //并且直接在ret数组上覆盖掉原有值即可
        int r = 1;
        for (int i = len - 1; i >= 0; i--) {
            ret[i] *= r;
            r *= nums[i];
        }
        return ret;
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        int[] res = new Case238Solution().productExceptSelf(nums);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + ",");
        }
    }
}
