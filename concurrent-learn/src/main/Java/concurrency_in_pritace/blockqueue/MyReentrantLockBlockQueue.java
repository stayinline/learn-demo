package concurrency_in_pritace.blockqueue;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 基于 ReentrantLock 实现阻塞队列
 *
 * @author hemaoling
 */
public class MyReentrantLockBlockQueue<T> implements MyBlockQueue<T> {

    private T[] queue;
    private int tail = 0;
    private int head = 0;
    private volatile int size = 0;

    ReentrantLock lock = new ReentrantLock();
    Condition full = lock.newCondition();
    Condition empty = lock.newCondition();

    public MyReentrantLockBlockQueue(int cap) {
        queue = (T[]) new Object[cap];
    }

    @Override
    public void add(T t) throws InterruptedException {
        lock.lock();
        try {
            while (size == queue.length) {
                System.out.println("队列满了，放不进去，要等");
                full.await();
            }
            queue[tail] = t;
            size++;
            tail = (tail + 1) % size;
            System.out.println("放入一个元素，e=" + t);
            empty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public T poll() throws InterruptedException {
        T t;
        lock.lock();
        try {
            while (size == 0) {
                System.out.println("队列为空，拿不到元素，要等");
                empty.await();
            }
            t = queue[head];
            size--;
            if (size == 0) {
                head = 0;
            } else {
                head = (head + 1) % size;
            }
            System.out.println("拿走一个元素，e=" + t);
            full.signalAll();
        } finally {
            lock.unlock();
        }
        return t;
    }


    @Override
    public int size() {
        lock.lock();
        try {
            return Math.abs(tail - head) + 1;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        lock.lock();
        try {
            return size() == 0;
        } finally {
            lock.unlock();
        }
    }
}
