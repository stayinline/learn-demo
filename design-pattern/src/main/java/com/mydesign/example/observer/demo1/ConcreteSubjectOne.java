package com.mydesign.example.observer.demo1;

/**
 * @author hemaoling
 */
public class ConcreteSubjectOne implements Observer {
    @Override
    public void update(Message message) {
        System.out.println("ConcreteSubjectOne is notified!!!");
    }
}
