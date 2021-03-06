package threadlocal;

import java.util.concurrent.CountDownLatch;

public class Demo {
    public static void main(String[] args) throws InterruptedException {

        int threads = 3;
        CountDownLatch countDownLatch = new CountDownLatch(threads);
        InnerClass innerClass = new InnerClass();
        for (int i = 1; i <= threads; i++) {
            new Thread(() -> {
                for (int j = 0; j < 4; j++) {
                    innerClass.add(String.valueOf(j));
                    innerClass.print();
                }
                innerClass.set("hello world");
                countDownLatch.countDown();
            }, "thread - " + i).start();
        }
        countDownLatch.await();

    }

    private static class InnerClass {

        public void add(String newStr) {
            StringBuilder str = Counter.counter.get();
            Counter.counter.set(str.append(newStr));
        }

        public void print() {
            System.out.printf("Thread name:%s , ThreadLocal hashcode:%s, Instance hashcode:%s, Value:%s\n",
                    Thread.currentThread().getName(),
                    Counter.counter.hashCode(),
                    Counter.counter.get().hashCode(),
                    Counter.counter.get().toString());
        }

        public void set(String words) {
            Counter.counter.set(new StringBuilder(words));
            System.out.printf("Set, Thread name:%s , ThreadLocal hashcode:%s,  Instance hashcode:%s, Value:%s\n",
                    Thread.currentThread().getName(),
                    Counter.counter.hashCode(),
                    Counter.counter.get().hashCode(),
                    Counter.counter.get().toString());
        }
    }

    private static class Counter {

        private static ThreadLocal<StringBuilder> counter = ThreadLocal.withInitial(StringBuilder::new);

    }

    /* 输出的结果：
     * Thread name:thread - 1 , ThreadLocal hashcode:1748370689, Instance hashcode:936310663, Value:0
     * Thread name:thread - 3 , ThreadLocal hashcode:1748370689, Instance hashcode:1372931938, Value:0
     * Thread name:thread - 2 , ThreadLocal hashcode:1748370689, Instance hashcode:786286205, Value:0
     * Thread name:thread - 3 , ThreadLocal hashcode:1748370689, Instance hashcode:1372931938, Value:01
     * Thread name:thread - 3 , ThreadLocal hashcode:1748370689, Instance hashcode:1372931938, Value:012
     * Thread name:thread - 1 , ThreadLocal hashcode:1748370689, Instance hashcode:936310663, Value:01
     * Thread name:thread - 3 , ThreadLocal hashcode:1748370689, Instance hashcode:1372931938, Value:0123
     * Thread name:thread - 2 , ThreadLocal hashcode:1748370689, Instance hashcode:786286205, Value:01
     * Set, Thread name:thread - 3 , ThreadLocal hashcode:1748370689,  Instance hashcode:1595061032, Value:hello world
     * Thread name:thread - 1 , ThreadLocal hashcode:1748370689, Instance hashcode:936310663, Value:012
     * Thread name:thread - 2 , ThreadLocal hashcode:1748370689, Instance hashcode:786286205, Value:012
     * Thread name:thread - 1 , ThreadLocal hashcode:1748370689, Instance hashcode:936310663, Value:0123
     * Thread name:thread - 2 , ThreadLocal hashcode:1748370689, Instance hashcode:786286205, Value:0123
     * Set, Thread name:thread - 2 , ThreadLocal hashcode:1748370689,  Instance hashcode:538590678, Value:hello world
     * Set, Thread name:thread - 1 , ThreadLocal hashcode:1748370689,  Instance hashcode:929423759, Value:hello world
     *
     * 注意：
     * 1、使用 set(T t) 方法后，ThreadLocal 变量所指向的 StringBuilder 实例被替换
     */

}
