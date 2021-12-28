package com.mydesign.example.observer.demo1;

import java.util.ArrayList;
import java.util.List;

public class ConcreteSubject implements Subject {

    private List<Observer> observerList = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removerObserver(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObserver(Message message) {
        for (Observer o : observerList) {
            o.update(message);
        }
    }
}
