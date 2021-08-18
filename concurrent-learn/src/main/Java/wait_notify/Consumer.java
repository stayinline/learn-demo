package wait_notify;

public class Consumer {

    private MyStack myStack;

    public Consumer(MyStack myStack) {
        this.myStack = myStack;
    }

    public String popValue() {
        return myStack.pop();
    }
}