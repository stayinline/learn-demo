package com.mydesign.example.observer.demo1;

public interface Subject {


    void registerObserver(Observer observer);

    void removerObserver(Observer observer);

    void notifyObserver(Message message);
}
