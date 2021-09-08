package concurrency_in_pritace.demo1;

import javax.lang.model.element.VariableElement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Test {
    private static ExecutorService service = new ThreadPoolExecutor(2, 2, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());


    public static void main(String[] args) {
        //testCallable();
        for (int i = 0; i < 20; i++) {
            testRunnable();
        }
        testInvokeAll();

    }

    private static void testInvokeAll() {
        Callable<List<TestData>> callable = () -> {
            System.out.println("处理业务");
            return null;
        };

        List<Callable<List<TestData>>> list = new ArrayList<>();
        list.add(callable);
        List<Future<List<TestData>>> futures = null;
        try {
            futures = service.invokeAll(list);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assert futures != null;
        for (Future<List<TestData>> future : futures) {
            try {
                List<TestData> testData = future.get(3000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                // 重置中断状态
                Thread.currentThread().interrupt();
                // 失败的cancel掉即可
                future.cancel(true);
            } catch (TimeoutException e) {
                future.cancel(true);
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    private static void testRunnable() {
        // Runnable 不支持返回值
        Runnable runnable = () -> System.out.println("处理业务...");
        Future<?> future = service.submit(runnable);
        try {
            //future.get();
            Object o = future.get(3000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            // 重置中断状态
            Thread.currentThread().interrupt();
            // 失败的cancel掉即可
            future.cancel(true);
        } catch (TimeoutException e) {
            future.cancel(true);
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void testCallable() {
        Callable<List<TestData>> callable = () -> {
            System.out.println("处理业务");
            return null;
        };

        Future<List<TestData>> submit = service.submit(callable);
        try {
            List<TestData> testData = submit.get();
            submit.get(3000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            // 重置中断状态
            Thread.currentThread().interrupt();
            // 失败的cancel掉即可
            submit.cancel(true);
        } catch (TimeoutException e) {
            submit.cancel(true);
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
