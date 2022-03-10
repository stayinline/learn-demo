package wait_notify.printAB;

/**
 * 用wait/notifyAll实现两个线程交替打印AB
 *
 * @author hemaoling
 */
public class PrintAB {
    private volatile boolean A_CAN = true;

    private synchronized void printA() {
        while (!A_CAN) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.print("A - ");

        A_CAN = false;
        notifyAll();
    }


    private synchronized void printB() {
        while (A_CAN) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.print("B - ");

        A_CAN = true;
        notifyAll();

    }

    public static void main(String[] args) {
        PrintAB printAB = new PrintAB();
        TestTaskA testTaskA = new TestTaskA(printAB);
        TestTaskB testTaskB = new TestTaskB(printAB);
        while (true) {
            testTaskA.run();
            testTaskB.run();
        }

    }

    static class TestTaskA implements Runnable {
        private PrintAB printAB;

        public TestTaskA(PrintAB printAB) {
            this.printAB = printAB;
        }

        @Override
        public void run() {
            printAB.printA();
        }
    }

    static class TestTaskB implements Runnable {
        private PrintAB printAB;

        public TestTaskB(PrintAB printAB) {
            this.printAB = printAB;
        }

        @Override
        public void run() {
            printAB.printB();
        }
    }
}
