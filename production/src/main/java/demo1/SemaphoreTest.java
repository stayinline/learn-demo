package demo1;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    public SemaphoreTest() {
    }

    public static void test1() {
        Semaphore semaphore = new Semaphore(3, true);

        for (int i = 0; i < 6; ++i) {
            (new Thread(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println("-----" + threadName + " come to part------");
                if (semaphore.availablePermits() == 0) {
                    System.out.println(threadName + " coming ,but there is no space， wait......");
                }

                try {
                    semaphore.acquire();
                    System.out.println(threadName + " enter ");
                } catch (InterruptedException var4) {
                    var4.printStackTrace();
                }

                System.out.println("after " + threadName + " enter ,there is " + semaphore.availablePermits() + " space");

                try {
                    Thread.sleep((long) (new Random()).nextInt(10000));
                } catch (InterruptedException var3) {
                    var3.printStackTrace();
                }

                System.out.println(threadName + " exit ");
                semaphore.release();
                System.out.println("after " + threadName + " exit ,there is " + semaphore.availablePermits() + " space");
            })).start();
        }

    }

    public static void test2() {
        Semaphore semaphore = new Semaphore(10);
        Executor executor = Executors.newFixedThreadPool(40);

        for (int i = 0; i < 40; ++i) {
            executor.execute(new demo1.SemaphoreTest.GetDataFromDb(semaphore));
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
