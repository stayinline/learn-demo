package com.mydesign.example.decorator.demo2;

public class RedBorderShapeDercorator extends ShapeDecorator {

    private Shape decoratorShape;

    public RedBorderShapeDercorator(Shape decoratorShape) {
        super(decoratorShape);
    }

    @Override
    public void drow() {
        super.drow();
        setRedBorder(decoratorShape);
    }

    private void setRedBorder(Shape decoratorShape) {
        System.out.println("边界的颜色是：红色");
    }
}
