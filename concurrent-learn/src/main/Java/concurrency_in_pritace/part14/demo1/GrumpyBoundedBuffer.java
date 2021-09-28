package concurrency_in_pritace.part14.demo1;

import concurrency_in_pritace.part14.BaseBoundedBuffer;

/**
 * v1: 是一个错误示例
 *
 * @author hemaoling
 */
public class GrumpyBoundedBuffer<V> extends BaseBoundedBuffer<V> {
    public GrumpyBoundedBuffer(int capacity) {
        super(capacity);
    }

    /**
     * 注意：
     * 抛异常并不是一个合适的选择！！！
     * <p>
     * 原因：
     * （1）异常会导致调用者不知道如何处理这个异常
     * （2）异常会导致buf丢失一些特性，比如FIFO的时候，就保留不了这种特性
     *
     * @param v
     * @throws BufferFullException
     */
    public synchronized void put(V v) throws BufferFullException {
        if (isFull()) {
            throw new BufferFullException();
        }
        doPut(v);
    }

    public synchronized V take() throws BufferEmptyException {
        if (isEmpty()) {
            throw new BufferEmptyException();
        }
        return doTake();
    }
}
