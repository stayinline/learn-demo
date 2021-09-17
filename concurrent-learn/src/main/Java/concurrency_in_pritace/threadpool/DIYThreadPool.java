package concurrency_in_pritace.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 实现一个统计每个任务执行时间的线程池
 * <p>
 * 要实现线程执行前后都能访问一个变量，所以用ThreadLocal来实现
 *
 * @author hemaoling
 */
public class DIYThreadPool extends ThreadPoolExecutor {

    private static final ThreadLocal<Long> startTime = new ThreadLocal<>();

    private final AtomicLong totalTime = new AtomicLong();

    public DIYThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                         BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);

        startTime.set(System.nanoTime());
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {

        try {
            long endTime = System.nanoTime();
            long execTime = endTime - startTime.get();
            totalTime.addAndGet(execTime);
            System.out.println("线程的总执行时长：" + execTime + " ns");

        } catch (Exception e) {
            // 处理异常
        } finally {
            // 这种可能会出现问题的代码，最好使用try{} finally{} 执行
            super.afterExecute(r, t);
        }

    }

    @Override
    protected void terminated() {
        super.terminated();
        startTime.remove();
    }
}
