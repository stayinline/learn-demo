package com.mydesign.example.singleton;


import java.util.concurrent.atomic.AtomicLong;

/**
 * 懒汉模式的单例
 * --双重检查的实现
 *
 * @author hemaoling
 */
public class IdGenerator3 {

    private AtomicLong id = new AtomicLong(0);

    /**
     * 加 volatile 是为了解决代码编译时的指令重排序，导致所创建的对象可能未被初始化
     */
    private static volatile IdGenerator3 instance;

    private IdGenerator3() {
    }

    /**
     * 双重检查的方式
     */
    public static IdGenerator3 getInstance() {
        if (instance == null) {
            synchronized (IdGenerator3.class) {
                if (instance == null) {
                    /*
                     * 1、new一个对象
                     * 2、初始化这个对象，或者填充这个对象相关信息
                     * 3、返回这个对象的引用
                     * 这3步可能编译后是无序的
                     */
                    instance = new IdGenerator3();
                }
            }
        }
        return instance;
    }

    public Long getId() {
        return id.incrementAndGet();
    }

}
