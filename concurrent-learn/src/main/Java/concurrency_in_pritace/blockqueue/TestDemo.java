package concurrency_in_pritace.blockqueue;

import java.util.Random;

public class TestDemo {


    public static void main(String[] args) {
        MyBlockQueue<Integer> queue = new MySynchBlockQueue<>(4);


        long start = System.currentTimeMillis();
        test(queue);
        long end = System.currentTimeMillis();

        System.out.println("synchronized 实现用时：" + (end - start));
        MyBlockQueue<Integer> queue2 = new MyReentrantLockBlockQueue<>(4);

        long start2 = System.currentTimeMillis();
        test(queue2);
        long end2 = System.currentTimeMillis();
        System.out.println("ReentrantLock 实现用时：" + (end2 - start2));

    }

    private static void test(MyBlockQueue<Integer> queue) {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    queue.add(finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    int ele = queue.poll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
