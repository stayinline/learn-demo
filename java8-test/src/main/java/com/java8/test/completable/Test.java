package com.java8.test.completable;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Test {


    /**
     * 输出结果：
     * Invocation returned after 45 msecs
     * 正在查找商品。。。
     * Price is 0.80
     * Price returned after 1064 msecs
     * <p>
     * 可以发现：
     * shop.getPriceAsync 返回时间仅仅只有45毫秒
     */
    public static void test() {
        Shop shop = new Shop("BestShop");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime + " msecs");

        // 执行更多任务，比如查询其他商店
        doSomethingElse();
        // 在计算商品价格的同时
        try {
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }

    private static void doSomethingElse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void test2() {
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            System.out.println("这是第1个CompletableFuture在执行");
        });

        CompletableFuture<Long> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("这是第2个CompletableFuture在执行");
            return 2L;
        });

        // 想实现先执行future1，再执行future2，应该怎么办？？？
        // future1.thenCompose(future2); 这样报错了，因为
        Consumer<Void> c = (s) -> System.out.println("这是第2个CompletableFuture在执行");
        future1.thenAccept(c);
    }

    /**
     * 汇聚关系
     */
    public static void test3() throws ExecutionException, InterruptedException {

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            String value = "这是第1个CompletableFuture在执行";
            System.out.println(value + "长度是：" + value.length());
            System.out.println("substring(8)：" + value.substring(8));
            return value;
        });

        Supplier<Integer> s = () -> {
            System.out.println("这是第2个CompletableFuture在执行");
            return 8;
        };
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(s);
        CompletableFuture<Supplier<Integer>> future = CompletableFuture.completedFuture(s);

        BiFunction<String, Integer, String> function = (s1, b2) -> s1.substring(b2);

        future1.thenCombine(future2, function);

        String result = future1.get();
        System.out.println("结果是:" + result);

    }


    static void thenCombineExample() {
        String original = "Message";
        CompletableFuture cf = CompletableFuture.completedFuture(original)
                .thenApply(String::toUpperCase)
                .thenCombine(CompletableFuture.completedFuture(original)
                                .thenApply(String::toLowerCase),
                        (s1, s2) -> s1 + s2);

        CompletableFuture cf2 = CompletableFuture.completedFuture(original)
                .thenApply(String::toUpperCase)
                .thenCombine(CompletableFuture.completedFuture(2L)
                                .thenApply(a -> a * 2),
                        (s1, s2) -> s1 + s2.toString());
        String result = cf.getNow(null).toString();
        System.out.println("result=" + result);
        // result=MESSAGEmessage

        String result2 = cf2.getNow(null).toString();
        System.out.println("result2=" + result2);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        test();
//        System.out.println(Runtime.getRuntime().availableProcessors());
//        test2();
//        thenCombineExample();

        test3();
    }


}
