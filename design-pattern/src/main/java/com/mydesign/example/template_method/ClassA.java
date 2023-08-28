package com.mydesign.example.template_method;

public class ClassA extends AbstractParentClass {
    @Override
    public void method1() {

    }

    @Override
    public void method2() {

    }

    @Override
    public void method3() {

    }

    @Override
    public void method(ICallBack callBack) {
        System.out.println("这里是ClassA的方法，准备回调");
        callBack.call1();
    }
}
