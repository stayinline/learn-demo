package wait_notify;

import java.util.ArrayList;
import java.util.List;

public class MyStack {

    private List<String> list = new ArrayList<>();

    synchronized void push() {
        try {
            while (list.size() == 1) {
                System.out.println(Thread.currentThread().getName() + " can not push, threr is one value!");
                this.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        list.add("value" + Math.random());

        this.notifyAll();

        System.out.println("after push, list.size()=" + list.size());
    }


    synchronized String pop() {
        try {
            while (list.size() == 0) {
                System.out.println(Thread.currentThread().getName() + " can not pop, threr is no value!");
                this.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String value = list.get(0);
        list.remove(0);
        this.notifyAll();

        System.out.println("after pop, list.size()=" + list.size());
        return value;
    }

}