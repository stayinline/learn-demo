package com.mydesign.example.singleton;


import java.util.concurrent.atomic.AtomicLong;

/**
 * 单例模式
 * --枚举的实现
 *
 * @author hemaoling
 */
public enum IdGenerator5 {
    INSTANCE;

    private AtomicLong id = new AtomicLong(0);

    public Long getId() {
        return id.incrementAndGet();
    }

}
