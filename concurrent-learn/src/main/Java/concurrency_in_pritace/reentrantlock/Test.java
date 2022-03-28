package concurrency_in_pritace.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 的 state 存储的是当前这个锁被线程重入的次数
 *
 * @author hemaoling
 */
public class Test {


    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        new Thread(() -> {
            lock.lock();

            lock.lock();

            System.out.println(lock.getHoldCount()); //2

        }).start();
    }
}
