package wait_notify;

public class Product {

    private MyStack myStack;

    public Product(MyStack myStack) {
        this.myStack = myStack;
    }

    public void pushValue() {
        myStack.push();
    }
}