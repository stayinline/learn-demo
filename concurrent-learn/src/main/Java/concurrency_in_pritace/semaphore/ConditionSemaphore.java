package concurrency_in_pritace.semaphore;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用condition模拟实现 Semaphore
 * <p>
 * 注意：
 * 实际的 Semaphore 是基于AQS实现的
 *
 * @author hemaoling
 */
public class ConditionSemaphore {

    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private int permits;

    ConditionSemaphore(int permits) {
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
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

}
