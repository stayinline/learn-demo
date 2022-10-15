package com.java8.test.duotai.interfacePak;


/**
 * 这里展示 ：采用接口实现的多态
 * Animal 是接口
 */
public class TestMain {

    public static void main(String[] args) {

        show();

        System.out.println("--------------分界线--------------");

        show2(new Cat());
        show2(new Dog());
        /**
         * 输出结果完全一致：
         *
         * 我是猫，要吃鱼
         * 我是猫，会抓老鼠®
         * 我是狗，要吃骨头
         * 我是狗，会看家
         * --------------分界线--------------
         * 我是猫，要吃鱼
         * 我是猫，会抓老鼠
         * 我是狗，要吃骨头
         * 我是狗，会看家
         */
    }

    public static void show() {
        Cat animal = new Cat();
        animal.eat();
        animal.work();


        Dog animal2 = new Dog();
        animal2.eat();
        animal2.work();

    }


    public static void show2(Animal animal) {
        if (null == animal) {
            System.out.println("错误");
        }

        /**
         * 注意这个 eat 方法，是在父类中声明的.
         * Cat 对象的 animal 传递过来，就会去调用 Cat 的eat方法
         * Dog 对象的 animal 传递过来，就会去调用 Dog 的eat方法
         *
         * 我们不需要再 instanceof 来判断对象到底是哪个子类，就能直接调用该子类重写的方法。
         * 这就是多态!!!!!
         */
        animal.eat();

        /**  work 方法是子类特有的，父类没有，所以不能使用多态，
         * 需要自己判断参数传的是什么对象，
         * 然后将参数强转成子类，才能调用他们扩展的的work方法
         */
        if (animal instanceof Cat) {
            ((Cat) animal).work();
        } else if (animal instanceof Dog) {
            ((Dog) animal).work();
        }
    }

}
