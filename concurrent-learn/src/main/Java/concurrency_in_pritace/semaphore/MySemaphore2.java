package concurrency_in_pritace.semaphore;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 采用这个可以实现想要的效果，但并不是真正的Semaphore的实现方式
 */
class MySemaphore2 {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private int permits;

    public MySemaphore2(int permits) {
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
            while (permits <= 0) {
                //当许可全都被申请的时候，增加一个等待，让其他线程全部在这里等着
                condition.await();
            }
            permits--;
        } finally {
            lock.unlock();
        }
    }

    public void release() throws InterruptedException {
        lock.lock();
        try {
            permits++;
            //当有任意一个许可全都被释放的时候，唤醒所有等待线程
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}

