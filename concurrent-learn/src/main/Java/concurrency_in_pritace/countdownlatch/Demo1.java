package concurrency_in_pritace.countdownlatch;


import java.util.concurrent.CountDownLatch;


/**
 * CountDownLatch允许一个或多个线程等待其他线程完成操作
 * 基本介绍：
 * //CountDownLatch的构造函数接收一个int类型的参数作为计数器，如果你想等待N个点完成，这里就传入N。
 * CountDownLatch latch = new CountDownLatch(10);
 * <p>
 * // countDown()每调用一次，构造函数传入的N值就会减一
 * latch.countDown();
 * <p>
 * //await方法 会阻塞当前线程，直到N变成零
 * // 由于countDown方法可以用在任何地方，所以这里说的N个点，可以是N个线程，也可以是1个线程里的N个执行步骤
 * latch.await();
 */
public class Demo1 {

    public static void main(String[] args) throws InterruptedException {

//        test1();
        test2();
//        test3();
    }

    private static void test1() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);

        latch.countDown();
        System.out.println("1, 剩余 " + latch.getCount());
        Thread.sleep(1000);

        latch.countDown();
        System.out.println("2, 剩余 " + latch.getCount());
        Thread.sleep(1000);

        latch.countDown(); // 并不会报错
        System.out.println("3, 剩余 " + latch.getCount());
        Thread.sleep(1000);

        latch.await();

        System.out.println("after await, 剩余 " + latch.getCount());

        /**
         * 1, 剩余 1
         * 2, 剩余 0
         * 3, 剩余 0
         * after await, 剩余 0
         */

    }

    private static void test2() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(2);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " before execute");
                    Thread.sleep(3000);
                    latch.countDown();
                    System.out.println(Thread.currentThread().getName() + " after execute");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

        }
        System.out.println("开始等任意两个线程执行结束....");
        latch.await();
        System.out.println("等到了两个线程执行完毕,继续执行主线程");


    }
}
