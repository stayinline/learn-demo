package concurrency_in_pritace.deadlock;

import java.util.Objects;

/**
 * @author hemaoling
 */
public class DeadLockTest {


    public static void main(String[] args) {
        Thread t1 = new Thread(new MyThread(true));//建立一个线程
        Thread t2 = new Thread(new MyThread(false));//建立另一个线程
        t1.start();//启动一个线程
        t2.start();//启动另一个线程
    }

    static class MyThread implements Runnable {
        private boolean flag;

        public MyThread(boolean flag) {
            this.flag = flag;
        }

        @Override
        public void run() {
            if (flag) {
                while (true) {
                    synchronized (TestLock.o1) {
                        System.out.println("I have o1,wait for o2");
                        synchronized (TestLock.o2) {
                            System.out.println("get o2");
                        }
                    }
                }
            } else {
                while (true) {
                    synchronized (TestLock.o2) {
                        System.out.println("I have o2,wait for o1");
                        synchronized (TestLock.o1) {
                            System.out.println("get o1");
                        }
                    }
                }
            }
        }
    }

    static class TestLock {
        public static Object o1 = new Object();
        public static Object o2 = new Object();
    }
}
