package com.mydesign.example.decorator.demo2;

/**
 * 每个图形在画的时候，添加一个红色的边界
 */
public class DecoratorDemo {

    public static void main(String[] args) {
        Shape circle = new Circle();
        ShapeDecorator redCircle = new RedBorderShapeDercorator(new Circle());
        ShapeDecorator redRectange = new RedBorderShapeDercorator(new Rectangle());

        System.out.println("没边界的时候：");
        circle.drow();

        System.out.println();
        System.out.println("有边界的时候：");
        redCircle.drow();
        redRectange.drow();

        /*
         * 没边界的时候：
         * 画圆
         *
         * 有边界的时候：
         * 画圆
         * 边界的颜色是：红色
         * 画长方形
         * 边界的颜色是：红色
         */
    }
}
