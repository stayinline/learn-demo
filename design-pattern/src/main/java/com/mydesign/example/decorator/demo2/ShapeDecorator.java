package com.mydesign.example.decorator.demo2;

public abstract class ShapeDecorator implements Shape {
    private Shape decoratorShape;

    public ShapeDecorator(Shape decoratorShape) {
        this.decoratorShape = decoratorShape;
    }

    @Override
    public void drow() {
        decoratorShape.drow();
    }
}
