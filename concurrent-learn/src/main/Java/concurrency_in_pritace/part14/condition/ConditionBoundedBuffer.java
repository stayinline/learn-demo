package concurrency_in_pritace.part14.condition;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * v3正确示例
 * 两个条件谓词，更容易理解
 *
 * @author hemaoling
 */
public class ConditionBoundedBuffer<V> {
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    private final V[] buf;
    private int tail;
    private int head;
    private int count;

    public ConditionBoundedBuffer(int capacity) {
        this.buf = (V[]) new Object[capacity];
    }

    public void put(V v) throws InterruptedException {
        lock.lock();
        try {
            // 只要队列为满，就一直await
            while (count == buf.length) {
                notFull.await();
            }
            buf[tail] = v;
            if (++tail == buf.length) {
                tail = 0;
            }
            count++;
            // 不满就put一个，然后一定是最少有一个元素，所以是 signal，而不是signalAll
            // 并且signal更高效
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public V take() throws InterruptedException {
        V v;
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            v = buf[head];
            if (++head == buf.length) {
                tail = 0;
            }
            count--;
            notFull.signal();
        } finally {
            lock.unlock();
        }
        return v;
    }

}
