package concurrency_in_pritace.cas;


/**
 * 模拟使用一下 MyCASInteger
 * 这个是CAS使用相关问题的核心，需要程序员控制CAS基础类来达到自己想要的效果
 */

public class MyCASCounter {
    private MyCASInteger value;

    public MyCASCounter(int value) {
        this.value = new MyCASInteger(value);
    }

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



    public static void main(String[] args) {
        MyCASCounter val = new MyCASCounter(0);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                val.increment();
            }
            ).start();
        }
        System.out.println("val=" + val.getValue());
    }


    /**
     * 模拟实现CAS
     *
     * @author hemaoling
     */
    private static class MyCASInteger {

        private int value;

        public MyCASInteger(int value) {
            this.value = value;
        }

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
    }


}
