package concurrency_in_pritace.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * test1、test2、test3 执行结果基本一致
 *
 * @author hemaoling
 */
public class TestDemo2 {
    static ExecutorService executor = Executors.newFixedThreadPool(30);


    public static void main(String[] args) {
//        test1();
//        test2();
        test3();

        executor.shutdown();
    }

    private static void test1() {
        MySemaphore1 semaphore = new MySemaphore1(10);
        for (int i = 0; i < 50; i++) {
            executor.execute(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("execute");
                    Thread.sleep(1000);
                    semaphore.release();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            });

        }
    }

    private static void test2() {
        MySemaphore2 semaphore = new MySemaphore2(10);
        for (int i = 0; i < 50; i++) {
            executor.execute(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("execute");
                    Thread.sleep(1000);
                    semaphore.release();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            });

        }
    }


    private static void test3() {
        MySemaphore3 semaphore = new MySemaphore3(10);
        for (int i = 0; i < 50; i++) {
            executor.execute(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("execute");
                    Thread.sleep(1000);
                    semaphore.release();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            });

        }
    }

}
