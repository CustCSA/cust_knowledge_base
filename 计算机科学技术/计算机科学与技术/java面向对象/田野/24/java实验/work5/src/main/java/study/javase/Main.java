package study.javase;

import study.javase.model.Circle;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double radius = scanner.nextDouble();
        Circle circle = new Circle(radius);
        System.out.printf(circle.getArea() + " " + circle.getPerimeter());
    }
}
