package concurrency_in_pritace.semaphore;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 运行结果：
 * 第11个线程执行的时候就超过了许可，为什么？
 * <p>
 * 这里只对permits的加减操作做了加锁操作，当但是已获得许可的线程正在执行的时候，没办法阻塞其他来抢夺许可的线程，
 * 所以前10个线程可以直接获得许可并执行，第11个线程就失败了。
 */
class MySemaphore1 {
    private final Lock lock = new ReentrantLock();
    private int permits;

    public MySemaphore1(int permits) {
        lock.lock();
        try {
            this.permits = permits;
        } finally {
            lock.unlock();
        }
    }

    public void acquire() throws InterruptedException {
        lock.lock();
        try {
            if (permits > 0) {
                permits--;
            } else {
                throw new InterruptedException("超过了许可数");
            }
        } finally {
            lock.unlock();
        }
    }

    public void release() throws InterruptedException {
        lock.lock();
        try {
            permits++;
        } finally {
            lock.unlock();
        }
    }
}