package com.java8.test.duotai.interfacePak;

class Dog implements Animal {
    @Override
    public void eat() {
        System.out.println("我是狗，要吃骨头");
    }

    public void work() {
        System.out.println("我是狗，会看家");
    }
}