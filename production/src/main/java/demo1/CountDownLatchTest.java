package demo1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {
    public CountDownLatchTest() {
    }

    public static void test1() {
        CountDownLatch latch = new CountDownLatch(3);
        (new TestThread(latch, "thread_1")).start();
        (new TestThread(latch, "thread_2")).start();
        (new TestThread(latch, "thread_3")).start();

        try {
            latch.await();
            System.out.println("after await, latch= " + latch.getCount());
        } catch (InterruptedException var2) {
            var2.printStackTrace();
        }

    }

    public static void test2() {
        CountDownLatch latch = new CountDownLatch(3);
        (new TestThread(latch, "thread_1")).start();
        (new TestThread(latch, "thread_2")).start();

        try {
            latch.await(5000L, TimeUnit.MILLISECONDS);
            System.out.println("after 5 s  await, latch= " + latch.getCount());
        } catch (InterruptedException var2) {
            var2.printStackTrace();
        }

    }

    public static void test3() {
        CountDownLatch latch = new CountDownLatch(3);
        TestThread thread1 = new TestThread(latch, "thread_1");
        thread1.start();
        Thread waitThread = new Thread(() -> {
            try {
                System.out.println("waitThread is await.........");
                latch.await();
            } catch (InterruptedException var2) {
                System.out.println("waitThread is interrupted!!!");
                var2.printStackTrace();
            }

        });
        waitThread.start();
        waitThread.interrupt();
        System.out.println(latch);
    }

    public static void main(String[] args) {
        test3();
    }

    static class TestThread extends Thread {
        private CountDownLatch latch;

        TestThread(CountDownLatch latch, String name) {
            this.latch = latch;
            Thread.currentThread().setName(name);
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "is runing...");
        }
    }
}
