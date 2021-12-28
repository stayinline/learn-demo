package com.mydesign.example.observer.demo1;


/**
 * 这是一个简单的观察者模式的框架
 */
public class Test {

    public static void main(String[] args) {
        ConcreteSubject concreteSubject = new ConcreteSubject();
        concreteSubject.registerObserver(new ConcreteSubjectOne());
        concreteSubject.registerObserver(new ConcreteSubjectTwo());
        concreteSubject.notifyObserver(new Message());
    }
}
