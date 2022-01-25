package com.mydesign.example.singleton;


import java.util.concurrent.atomic.AtomicLong;

/**
 * 懒汉模式的单例
 *
 * @author hemaoling
 */
public class IdGenerator2 {

    private AtomicLong id = new AtomicLong(0);
    private static IdGenerator2 instance;

    private IdGenerator2() {
    }

    /**
     * 这种实现方式并发度很低
     *
     * @return
     */
    public static synchronized IdGenerator2 getInstance() {
        if (instance == null) {
            instance = new IdGenerator2();
        }
        return instance;
    }

    public Long getId() {
        return id.incrementAndGet();
    }

}
