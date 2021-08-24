package com.mydesign.example.demo2;

public class B extends A {
    @Override
    public String print(A obj) {
        return("B  and  A");
    }

    public String print(B obj) {
        return("B  and  B");
    }
}
