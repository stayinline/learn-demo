package concurrency_in_pritace.aqs.demo1;

public class DemoApp {
    private static int v = 0;

    public static void main(String[] args) {
        v1();
        v2();
    }

    private static void v2() {
        MyLatch myLatch = new MyLatch();
        for (int i = 0; i < 1000; i++) {
            new MyWorkThread2(myLatch).run();
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("v= " + v);
    }

    private static void v1() {
        for (int i = 0; i < 1000; i++) {
            new MyWorkThread1().run();
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("v= " + v);
    }

    private static class MyWorkThread2 implements Runnable {
        private MyLatch myLatch;

        public MyWorkThread2(MyLatch myLatch) {
            this.myLatch = myLatch;
        }

        @Override
        public void run() {
            myLatch.await();
            try {
                v++;
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                myLatch.signal();
            }
        }
    }
    private static class MyWorkThread1 implements Runnable {
        @Override
        public void run() {
            try {
                v++;
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
