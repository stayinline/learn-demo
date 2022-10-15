package com.java8.test.duotai.interfacePak;

class Cat implements Animal {
    @Override
    public void eat() {
        System.out.println("我是猫，要吃鱼");
    }

    public void work() {
        System.out.println("我是猫，会抓老鼠");
    }
}  