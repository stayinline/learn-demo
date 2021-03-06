package concurrency_in_pritace.part14;


/**
 * @author hemaoling
 */
public abstract class BaseBoundedBuffer<V> {

    private final V[] buf;
    private int tail;
    private int head;
    private int count;

    public BaseBoundedBuffer(int capacity) {
        this.buf = (V[]) new Object[capacity];
    }

    protected synchronized final void doPut(V v) {
        buf[tail] = v;
        if (++tail == buf.length) {
            tail = 0;
        }
        count++;
    }

    protected synchronized final V doTake() {
        V v = buf[head];
        buf[head] = null;
        if (++head == buf.length) {
            head = 0;
        }
        count--;
        return v;
    }

    protected synchronized final boolean isFull() {
        return count == buf.length;
    }

    protected synchronized final boolean isEmpty() {
        return count == 0;
    }

}
