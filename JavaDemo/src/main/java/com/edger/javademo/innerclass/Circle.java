package com.edger.javademo.innerclass;

class Circle {
    private double radius = 0;
    public static int count = 1;

    public Circle(double radius) {
        this.radius = radius;
        getDraw().drawShape();
    }

    private Draw getDraw() {
        return new Draw();
    }

    class Draw {
        double radius = 2;
        int count = 2;
        public void drawShape() {
            System.out.println("draw shape.");
            System.out.println(radius);
            System.out.println(count);
            System.out.println(Circle.this.radius);
            System.out.println(Circle.count);
        }
    }
}
