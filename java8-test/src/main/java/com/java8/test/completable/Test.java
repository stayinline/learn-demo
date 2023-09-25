package com.java8.test.completable;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.*;

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
    public void test3() throws ExecutionException, InterruptedException {


        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            String value = "这是第1个CompletableFuture在执行";
            System.out.println(value + "s1的长度是：" + value.length());
            System.out.println("s1.substring(8)：" + value.substring(8));
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return value;
        });

        Supplier<Integer> s = () -> {
            System.out.println("这是第2个CompletableFuture在执行");
            return 8;
        };
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(s);


        // 从s1的结果中取[b2,end]的子串
        BiFunction<String, Integer, String> function = (s1, b2) -> s1.substring(b2);

        // 意思是将future1的执行结果，传递给future2，并执行function指定的具体操作
        CompletableFuture<String> combineFuture = future1.thenCombine(future2, function);

        // 注意这里是combineFuture执行get方法，要在这个结果上等才是期望的效果
        String result = combineFuture.get();
        System.out.println("结果是:" + result);

        /**
         * 这是第1个CompletableFuture在执行s1的长度是：25
         * s1.substring(8)：pletableFuture在执行
         * 这是第2个CompletableFuture在执行
         * (这里会等待3秒钟，然后紧接着打印下一行的内容)
         * 结果是:pletableFuture在执行
         */


        /*
         * 输出：
         * 这是第1个CompletableFuture在执行s1的长度是：25
         * s1.substring(8)：pletableFuture在执行
         * 这是第2个CompletableFuture在执行
         * 结果是:pletableFuture在执行
         */

        // thenAcceptBoth的效果是：等future1和future2都执行完，再执行function的操作，
        // 所以function接受两个参数，分别代表future1、future2的执行结果，
        // 这里把两个future的结果，打印一遍
        BiConsumer<String, Integer> c = (p1, p2) -> System.out.println("future1、future2的执行结果，p1=" + p1 + ",p2=" + p2);

        CompletableFuture result2 = future1.thenAcceptBoth(future2, c);
        result2.get();
        /*
         * future1、future2的执行结果，p1=这是第1个CompletableFuture在执行,p2=8
         */


        Runnable r3 = () -> System.out.println("这是第3个future在执行");
        CompletableFuture<Void> result3 = future1.runAfterBothAsync(future2, r3);
        result3.get();

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

    public void test4() throws ExecutionException, InterruptedException {

        CompletableFuture<String> futureFirst = CompletableFuture.supplyAsync(() -> {
            String value = "这是第1个CompletableFuture在执行";
            System.out.println(value);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return value;
        });


        Function<String, CompletableFuture<String>> function = (a) -> {
            String value = "这是第2个CompletableFuture在执行";
            return CompletableFuture.supplyAsync(()->{
                System.out.println(value);
                return value;
            });
        };


//        CompletableFuture<String> future = futureFirst.thenCompose(function);
        CompletableFuture<String> future = futureFirst.thenComposeAsync(function);

        String result = future.get();

        System.out.println("结果是:" + result);
    }




    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        test();
//        System.out.println(Runtime.getRuntime().availableProcessors());
//        test2();
//        thenCombineExample();

//        new Test().test3();

        new Test().test4();
    }


}
