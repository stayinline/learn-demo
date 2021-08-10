package com.myself.leetcode;

import java.util.*;

public class Case59 {

    //暴力法
    public static int[] maxSlidingWindow1(int[] nums, int k) {
        if (nums.length == 0 || k <= 0) {
            return new int[]{};
        }

        List<Integer> list = new ArrayList<>();
        int i = 0, j = k - 1;
        while (j < nums.length) {
            int max = findMax(nums, i, j);
            list.add(max);
            i++;
            j++;
        }
        int[] ret = new int[list.size()];
        for (int a = 0; a < list.size(); a++) {
            ret[a] = list.get(a);
        }
        return ret;
    }

    public static int findMax(int[] arr, int left, int right) {
        int ret = arr[left];
        for (int i = left + 1; i <= right; i++) {
            ret = Math.max(arr[i], ret);
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, -1, -3, 5, 3, 6, 7};

        int[] maxSlidingWindow = maxSlidingWindow(arr, 3);

        System.out.println(Arrays.toString(maxSlidingWindow));


    }


    // 双端队列
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || k <= 0) {
            return new int[]{};
        }
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        int i = 0, j = 0;
        j = dealFirstWindow(nums, k, deque, j);
        for (; j < nums.length; i++, j++) {
            // 删除 deque 中对应的 nums[i-1]，
            // 因为队列中头部的这个nums[i - 1]元素已经记录过了，窗口向后滑动的时候，应该删掉
            if (i > 0 && deque.peekFirst() == nums[i - 1]) {
                deque.removeFirst();
            }
            // 保持 deque 递减
            while (!deque.isEmpty() && deque.peekLast() < nums[j]) {
                deque.removeLast();
            }
            deque.addLast(nums[j]);
            // 记录窗口最大值
            res[i] = deque.peekFirst();
        }
        return res;
    }

    private static int dealFirstWindow(int[] nums, int k, Deque<Integer> deque, int j) {
        for (; j < k - 1; j++) {
            // 保持 deque 递减
            while (!deque.isEmpty() && deque.peekLast() < nums[j]) {
                deque.removeLast();
            }
            deque.addLast(nums[j]);
        }
        return j;
    }


}
