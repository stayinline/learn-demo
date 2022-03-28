package concurrency_in_pritace.blockqueue;

/**
 * @author hemaoling
 */
public interface MyBlockQueue<T> {

    void add(T t) throws InterruptedException;

    T poll() throws InterruptedException;

    int size();

    boolean isEmpty();

}
