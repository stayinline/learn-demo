package concurrency_in_pritace.part14.demo2;

import concurrency_in_pritace.part14.demo1.BaseBoundedBuffer;

/**
 * 正确示例：
 * 使用条件队列
 */
public class BoundedBuffer<V> extends BaseBoundedBuffer<V> {
    public BoundedBuffer(int capacity) {
        super(capacity);
    }

    public synchronized void put(V v) throws InterruptedException {
        while (isFull()) {
            wait();
        }
        doPut(v);
        notifyAll();
    }

    public synchronized V take() throws InterruptedException {
        while (isEmpty()) {
            wait();
        }
        return doTake();
    }
}
