package com.mydesign.example.strategy.demo1;

public class Context {
    private ServiceStrategyLambda strategy;

    public Context(ServiceStrategyLambda strategy) {
        this.strategy = strategy;
    }

    public boolean executeStrategy(String name) {
       return strategy.execute(name);
    }
}
