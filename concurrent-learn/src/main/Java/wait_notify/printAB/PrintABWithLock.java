package wait_notify.printAB;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用 Lock/Condition 实现两个线程交替打印AB
 *
 * @author hemaoling
 */
public class PrintABWithLock {
    private volatile boolean A_CAN = true;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private synchronized void printA() {
        lock.lock();
        try {
            while (!A_CAN) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.print("A - ");

            A_CAN = false;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }


    private synchronized void printB() {
        lock.lock();
        try {
            while (A_CAN) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.print("B - ");

            A_CAN = true;
            condition.signalAll();
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        PrintABWithLock printAB = new PrintABWithLock();
        TestTaskA2 testTaskA = new TestTaskA2(printAB);
        TestTaskB2 testTaskB = new TestTaskB2(printAB);
        while (true) {
            testTaskA.run();
            testTaskB.run();
        }

    }

    static class TestTaskA2 implements Runnable {
        private PrintABWithLock printAB;

        public TestTaskA2(PrintABWithLock printAB) {
            this.printAB = printAB;
        }

        @Override
        public void run() {
            printAB.printA();
        }
    }

    static class TestTaskB2 implements Runnable {
        private PrintABWithLock printAB;

        public TestTaskB2(PrintABWithLock printAB) {
            this.printAB = printAB;
        }

        @Override
        public void run() {
            printAB.printB();
        }
    }
}
