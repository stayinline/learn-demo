package concurrency_in_pritace.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestDemo {

    public static void main(String[] args) {
        test1();
//        test2();
//        System.out.println("####分割线####");
//        test3();
    }

    private static void test1() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2, 3, 10,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(20));
        threadPoolExecutor.execute(() -> {
            for (int i = 0; i < 2; i++) {
                System.out.println("I：" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadPoolExecutor.shutdownNow();
        System.out.println("ThreadPoolExecutor");

        /**
         * 输出结果：
         * I：0
         * ThreadPoolExecutor
         * java.lang.InterruptedException: sleep interrupted
         * 	at java.lang.Thread.sleep(Native Method)
         * 	at concurrency_in_pritace.threadpool.TestDemo$1.run(TestDemo.java:18)
         * 	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
         * 	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
         * 	at java.lang.Thread.run(Thread.java:748)
         * I：1
         *
         * 分析：
         *  关于I:1 为什么会输出？
         * try-catch 捕获了异常，所以后续的循环不会被中断
         *
         *  I:1 一定是在异常之后？
         * shutdownNow 执行的时候，线程池还sleep在i=0，此时响应中断并抛出异常，然后再执行循环
         */
    }


    private static void test2() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2, 5, 10,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(2));
        for (int i = 1; i < 7; i++) {
            threadPoolExecutor.execute(new TestTask2(i));
            System.out.println("队列大小：" + threadPoolExecutor.getQueue().size()
                    + "    线程数：" + threadPoolExecutor.getPoolSize());
        }
    }

    private static void test3() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2, 5, 10,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(1));
        for (int i = 0; i < 7; i++) {
            threadPoolExecutor.execute(new TestTask3(i));
            System.out.println("队列大小：" + threadPoolExecutor.getQueue().size()
                    + "    线程数：" + threadPoolExecutor.getPoolSize());
        }
    }

    static class TestTask2 implements Runnable {
        private int val;

        public TestTask2(int val) {
            this.val = val;
        }

        @Override
        public void run() {
            System.out.print(val + "-----  ");
        }
    }

    static class TestTask3 implements Runnable {
        private int val;

        public TestTask3(int val) {
            this.val = val;
        }

        @Override
        public void run() {
            System.out.print(val + "-----  ");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
