package concurrency_in_pritace.cancel.by_future;

import java.util.concurrent.*;

public class Test {


    private static final ExecutorService EXECUTOR_SERVICE =
            new ThreadPoolExecutor(2, 3, 4000,
                    TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1000));

    public void timedRun(Runnable runnable, long timeout, TimeUnit timeUnit) throws InterruptedException, ExecutionException {
        Future<?> future = EXECUTOR_SERVICE.submit(runnable);
        try {
            future.get(timeout, timeUnit);
        } catch (ExecutionException e) {
            // 应该将异常传递
            throw e;
        } catch (TimeoutException e) {
            // 任务应该被取消，此处放在finally块中执行
        } finally {
            // 需要注意：如果线程正在执行，那么将会被中断
            future.cancel(true);
        }
    }
}
