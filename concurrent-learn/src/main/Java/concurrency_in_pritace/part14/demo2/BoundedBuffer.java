package concurrency_in_pritace.part14.demo2;

import concurrency_in_pritace.part14.BaseBoundedBuffer;

/**
 * v2 正确示例：
 * 使用条件队列
 * <p>
 * 存在一个问题：
 * wait和notify的都是同一个对象，不容易理解，
 * 并且多线程时，对同一个队列的等待，可能是不同的条件谓词
 *
 * @author hemaoling
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
