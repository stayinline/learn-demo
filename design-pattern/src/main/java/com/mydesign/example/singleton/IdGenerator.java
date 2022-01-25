package com.mydesign.example.singleton;


import java.util.concurrent.atomic.AtomicLong;

/**
 * 饿汉模式的单例
 *
 * @author hemaoling
 */
public class IdGenerator {

    private AtomicLong id = new AtomicLong(0);
    private static final IdGenerator INSTANCE = new IdGenerator();

    private IdGenerator() {
    }

    public static IdGenerator getInstance() {
        return INSTANCE;
    }

    public Long getId() {
        return id.incrementAndGet();
    }

}
