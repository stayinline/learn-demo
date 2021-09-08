package concurrency_in_pritace.demo2;

import concurrency_in_pritace.demo1.TestData;

import java.util.concurrent.*;

public class CompletionServiceTest {
    private static ExecutorService service = new ThreadPoolExecutor(2, 2, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

    /**
     * 一组任务一起执行，并结合BlockingQueue的特性，获取结果
     *
     * @param args
     */
    public static void main(String[] args) {
        CompletionService<TestData> completionService = new ExecutorCompletionService<>(service);
        int count = 10;
        for (int i = 0; i < count; i++) {
            completionService.submit(() -> {
                System.out.println("处理业务");
                return new TestData();
            });
        }

        for (int i = 0; i < count; i++) {
            try {
                Future<TestData> future = completionService.take();
                Future<TestData> future2 = completionService.poll(3000, TimeUnit.MILLISECONDS);
                TestData testData = future.get();
                System.out.println("处理结果: " + testData.toString());
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
