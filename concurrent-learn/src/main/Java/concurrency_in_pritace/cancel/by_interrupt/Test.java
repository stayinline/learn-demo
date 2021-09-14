package concurrency_in_pritace.cancel.by_interrupt;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Test {

    public static void main(String[] args) {

        Producter producter = new Producter(new LinkedBlockingQueue<>());
        producter.run();


        // 经过很长的业务流程之后
        producter.cancel();
    }

    static class Producter implements Runnable {

        static BlockingQueue<Integer> QUEUE = null;

        public Producter(BlockingQueue<Integer> queue) {
            QUEUE = queue;
        }

        @Override
        public void run() {
            boolean interrupted = Thread.currentThread().isInterrupted();

            while (!interrupted) {
                try {
                    QUEUE.put(123);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void cancel() {
            Thread.currentThread().interrupt();
        }
    }
}
