package com.java8.test.duotai.inheritance;

class Dog extends Animal {
    @Override
    public void eat() {
        System.out.println("我是狗，要吃骨头");
    }

    public void work() {
        System.out.println("我是狗，会看家");
    }
}