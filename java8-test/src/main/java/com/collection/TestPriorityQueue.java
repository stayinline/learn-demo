package com.collection;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class TestPriorityQueue {

    public static void main(String[] args) {
        testAPI();


        int[] arr = {1, 2, 3, 4, 5, 5, 6, 4, 3, 55, 6, 3};

        System.out.println(Arrays.toString(topK(arr, 3).toArray()));

        System.out.println(Arrays.toString(topKSmall(arr, 5).toArray()));
    }

    private static void testAPI() {
        PriorityQueue<String> q = new PriorityQueue<>();
        q.add("Orange");
        q.add("Apple");
        q.add("Banana");


        System.out.println(q.contains("pair"));
        System.out.println(q.peek());

        System.out.println(q.size());

        q.offer("peach");

        Comparator<? super String> comparator = q.comparator();
        System.out.println(comparator);


        System.out.println(q.poll());
        System.out.println(q.poll());
        System.out.println(q.poll());
    }

    /**
     * 从数组中找出前k大的元素
     *
     * @param arr
     * @param k
     * @return 返回一个优先级队列，存储了前k大的元素
     */
    public static PriorityQueue<Integer> topK(int[] arr, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i : arr) {
            queue.add(i);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        return queue;
    }

    /**
     * 从数组中找出前k小的元素
     */
    public static PriorityQueue<Integer> topKSmall(int[] arr, int k) {
        // 逆序的优先队列，也就是大顶堆
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, Comparator.reverseOrder());

        // 对于数组中的每个元素进行处理
        for (int num : arr) {
            // 如果优先队列还没有满，就加入元素
            if (queue.size() < k) {
                queue.offer(num);
            } else if (num < queue.peek()) {
                // 如果优先队列已经满了，比较并决定是否替换最大元素
                queue.poll();
                queue.offer(num);
            }
        }
        return queue;
    }
}


