package concurrency_in_pritace.threadpool;

import java.util.concurrent.*;

public class Demo1 {


    /**
     * 创建一个固定大小的线程池
     * 并且是有界队列、以及"调用者运行"这个拒绝策略
     * 缺陷是：
     * 当达到最大线程数、且队列已满的时候，会有主线程执行新来的任务，导致主线程执行期间，不能接受其他新的任务而阻塞；
     * 如果是一个web服务器，那么将会一直阻塞到TCP层，到客户端，
     * 所以，应该控制主线程执行期间的请求
     */
    private static void demo1() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 1000, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1000));
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    }

    static class BoundedExecutor {

        private Executor executor;
        private final Semaphore semaphore;


        /**
         * 首先：bounded应该是线程池的最大线程数+队列总长度
         *
         * @param executor
         * @param bounded
         */
        BoundedExecutor(Executor executor, int bounded) {
            this.executor = executor;
            this.semaphore = new Semaphore(bounded);
        }

        public void submitTask(Runnable runnable) throws InterruptedException {
            semaphore.acquire();

            executor.execute(runnable);

            // 这里不能直接使用入参的runnable执行，拿不到返回值，无法执行release


        }


        /**
         * 这样一来，每一个线程acquire一次，semaphore减一，最终主线程会执行新到来的任务，
         * 然后再执行 semaphore.acquire() 的时候会阻塞；
         * 如此就实现了控制
         *
         * @param runnable
         * @throws InterruptedException
         */
        public void submitTask1(Runnable runnable) throws InterruptedException {
            semaphore.acquire();

            // 应该封一层，并用try{}finally{}的方式执行release
            executor.execute(() -> {
                try {
                    runnable.run();
                } finally {
                    semaphore.release();
                }
            });
        }

    }

    public static void main(String[] args) {
    }
}
