package com.mydesign.example.observer.demo1;

public class ConcreteSubjectTwo implements Observer {

    @Override
    public void update(Message message) {
        System.out.println("ConcreteSubject-Two is notified!!!");
    }
}
