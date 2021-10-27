package concurrency_in_pritace.cas;

/**
 * 模拟实现CAS
 *
 * @author hemaoling
 */
public class MyCASInteger {

    private int value;

    public synchronized int get() {
        return value;
    }

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            value = newValue;
        }
        return oldValue;
    }

    /**
     * compareAndSwap 返回的是 oldValue，
     * 所以 oldValue == expectedValue 才表示符合预期并且设置成功
     *
     * @param expectedValue
     * @param newValue
     * @return
     */
    public synchronized boolean compareAndSet(int expectedValue, int newValue) {
        return compareAndSwap(expectedValue, newValue) == expectedValue;
    }


    /**
     * 模拟使用一下 SimulatedCAS
     */

    private static class Counter {
        private MyCASInteger value;

        public int getValue() {
            return value.get();
        }

        public int increment() {
            int v;
            do {
                v = value.get();
            } while (v != value.compareAndSwap(v, v + 1));
            return v + 1;
        }
    }


}
