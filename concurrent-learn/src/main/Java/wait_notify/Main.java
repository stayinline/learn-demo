package wait_notify;

public class Main {
    public static void main(String[] args) {
        MyStack myStack = new MyStack();

        for (int i = 0; i < 3; i++) {
            Product product = new Product(myStack);
            Consumer consumer = new Consumer(myStack);

            ProductThread productThread = new ProductThread(product);
            ConsumerThread consumerThread = new ConsumerThread(consumer);

            productThread.start();
            consumerThread.start();
        }
    }

}