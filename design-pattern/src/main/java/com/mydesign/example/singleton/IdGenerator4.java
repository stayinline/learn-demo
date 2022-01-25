package com.mydesign.example.singleton;


import java.util.concurrent.atomic.AtomicLong;

/**
 * 单例模式
 * --静态内部类的实现
 *
 * @author hemaoling
 */
public class IdGenerator4 {
    private AtomicLong id = new AtomicLong(0);
    private static IdGenerator4 instance;

    private IdGenerator4() {
    }

    public static IdGenerator4 getInstanceByDoubleCheck() {
        return SingletonHolder.INSTANCE;
    }

    public Long getId() {
        return id.incrementAndGet();
    }


    private static class SingletonHolder {
        private static final IdGenerator4 INSTANCE = new IdGenerator4();
    }

}
