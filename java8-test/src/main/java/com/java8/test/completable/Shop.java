package com.java8.test.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Shop {

    public String name;
    public double price;

    public Shop(String name) {
        this.name = name;
    }

    public Shop(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Future<Double> getPriceAsync(String my_favorite_product) {
        CompletableFuture<Double> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("正在查找商品。。。");
            return 0.8;
        });
        return future;
    }
}
