package concurrency_in_pritace.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore最大的用处是控制并发度，也就是控制x个线程同时执行
 */
public class TestDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(10);

        ExecutorService executor = Executors.newFixedThreadPool(30);

        for (int i = 0; i < 50; i++) {
            executor.execute(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("execute,剩余semaphore " + semaphore.availablePermits() + ", 等待线程数 " + semaphore.getQueueLength());
                    Thread.sleep(1000);
                    semaphore.release();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            });

        }

        executor.shutdown();
        /**
         * 最大并行度10，线程池30个线程，50个任务
         * 运行结果：
         * 每个任务执行1秒(即sleep(1000))，所以会有10个一组，每隔一秒输出一次
         */
    }
}
