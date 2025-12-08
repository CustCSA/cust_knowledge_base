package study.javase.model;

public class Triangle implements Shape {
    private double a;
    private double b;
    private double c;
    private double area;
    private double perimeter;
    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.perimeter = a + b + c;
        double s = perimeter / 2;
        this.area = Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }
    @Override
    public double getArea() {
        return area;
    }

    @Override
    public double getPerimeter() {
        return perimeter;
    }
}
