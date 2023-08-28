package com.mydesign.example.template_method;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SubClassA  implements ICallBack {

    private AbstractParentClass parentClass;

    @Override
    public void call1() {
        parentClass.method1();

    }

    @Override
    public void call() {
        System.out.println("这里是SubClassA的回调方法");
        parentClass.method(this);

    }
}
