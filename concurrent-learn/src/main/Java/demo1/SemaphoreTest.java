package demo1;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author hemaoling
 */
public class SemaphoreTest {


    /**
     * 模拟停车场场景
     */
    public static void test1() {
        // 停车位个数
        Semaphore semaphore = new Semaphore(3, true);

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println("-----" + threadName + " come to part------");
                if (semaphore.availablePermits() == 0) {
                    System.out.println(threadName + " coming ,but there is no space， wait......");
                }
                try {
                    semaphore.acquire();
                    System.out.println(threadName + " enter ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("after " + threadName + " enter ,there is " + semaphore.availablePermits() + " space");
                try {
                    Thread.sleep(new Random().nextInt(10000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(threadName + " exit ");
                semaphore.release();
                System.out.println("after " + threadName + " exit ,there is " + semaphore.availablePermits() + " space");

            }).start();
        }


    }

    public static void test2() {
        final int POOL_SIZE = 10;
        final Semaphore semaphore = new Semaphore(POOL_SIZE);
        final int MAX_COUNT = 40;
        Executor executor = Executors.newFixedThreadPool(MAX_COUNT);
        for (int i = 0; i < MAX_COUNT; i++) {
            executor.execute(new GetDataFromDb(semaphore));
        }

    }

    static class GetDataFromDb extends Thread {
        Semaphore se;

        public GetDataFromDb(Semaphore semaphore) {
            se = semaphore;
        }

        @Override
        public void run() {
            try {
                // 获取信号量，如果不成功则会阻塞在这里，直到成功
                se.acquire();
                System.out.println(Thread.currentThread().getName() + " get data from db ......");
                Thread.sleep(new Random().nextInt(1000));
                // 处理完释放信号量
                se.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        test2();
    }
}
