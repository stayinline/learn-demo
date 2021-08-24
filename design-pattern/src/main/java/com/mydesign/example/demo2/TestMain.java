package com.mydesign.example.demo2;

public class TestMain {

    public static void main(String[] args) {
        A a1 = new A();
        A a2 = new B();
        B b = new B();
        C c = new C();
        D d = new D();

        /*
         * 多态调用优先级
         * this.(o)
         * super.(o)
         * this.((super)o)
         * super.((super)o)
         */
        System.out.println("1--" + a1.print(b));
        System.out.println("2--" + a1.print(c));
        System.out.println("3--" + a1.print(d));
        System.out.println("4--" + a2.print(b));
        System.out.println("5--" + a2.print(c));
        System.out.println("6--" + a2.print(d));
        System.out.println("7--" + b.print(b));
        System.out.println("8--" + b.print(c));
        System.out.println("9--" + b.print(d));
    }
}
