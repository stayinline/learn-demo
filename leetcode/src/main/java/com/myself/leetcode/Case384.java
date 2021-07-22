package com.myself.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Case384 {

    class Solution {

        private int[] originNums;
        private int[] nums;

        public Solution(int[] nums) {
            originNums = new int[nums.length];
            nums = new int[nums.length];
            originNums = nums.clone();
            this.nums = nums.clone();
        }

        /**
         * Resets the array to its original configuration and return it.
         */
        public int[] reset() {
            nums = Arrays.copyOf(originNums, originNums.length);
            return nums;
        }

        /**
         * Returns a random shuffling of the array.
         */
        public int[] shuffle1() {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                list.add(nums[i]);
            }

            for (int i = 0; i < nums.length; i++) {
                int removeIdx = new Random().nextInt(list.size());
                nums[i] = list.get(removeIdx);
                list.remove(removeIdx);
            }
            return nums;
        }

        /**
         * 洗牌算法
         *
         * @return
         */
        Random random = new Random();

        public int[] shuffle() {
            for (int i = 0; i < nums.length; i++) {
                swap(nums[i], randRange(i + 1, nums.length));
            }
            return nums;
        }

        private int randRange(int min, int max) {
            return random.nextInt(max - min) + min;
        }

        private void swap(int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

    }
}
