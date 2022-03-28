package concurrency_in_pritace.blockqueue;


/**
 * 基于 synchronized 实现阻塞队列
 *
 * @author hemaoling
 */
public class MySynchBlockQueue<T> implements MyBlockQueue<T> {

    private T[] queue;
    private int tail = 0;
    private int head = 0;
    private volatile int size = 0;

    public MySynchBlockQueue(int cap) {
        queue = (T[]) new Object[cap];
    }

    @Override
    public synchronized void add(T t) throws InterruptedException {
        while (size == queue.length) {
            System.out.println("队列满了，放不进去，要等");
            wait();
        }
        queue[tail] = t;
        size++;
        tail = (tail + 1) % size;
        System.out.println("放入一个元素，e=" + t);
        notifyAll();
    }

    @Override
    public synchronized T poll() throws InterruptedException {
        T t;
        while (size == 0) {
            System.out.println("队列为空，拿不到元素，要等");
            wait();
        }
        t = queue[head];
        size--;
        if (size == 0) {
            head = 0;
        } else {
            head = (head + 1) % size;
        }
        System.out.println("拿走一个元素，e=" + t);
        notifyAll();
        return t;
    }


    @Override
    public synchronized int size() {
        return Math.abs(tail - head) + 1;
    }

    @Override
    public synchronized boolean isEmpty() {
        return size() == 0;
    }
}
