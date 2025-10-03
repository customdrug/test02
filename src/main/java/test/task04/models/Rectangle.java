package test.task04.models;

import java.util.Objects;

public class Rectangle extends BaseFigure implements Shape {

    private final int a;
    private final int b;

    public Rectangle(int a, int b) {
        if (a <= 0 || b <= 0) {
            throw new IllegalArgumentException("Sides must be > 0");
        }
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    @Override
    public double calculatePerimeter() {
        return 2.0 * (a + b);
    }

    @Override
    public double calculateArea() {
        return (double) a * b;
    }

    @Override
    public String descriptionPl() {
        return "Prostokat o bokach " + a + "x" + b + ".";
    }

    @Override
    public String toStorageLine() {
        return ShapeType.RECTANGLE.code() + ";" + a + ";" + b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Rectangle)) {
            return false;
        }
        Rectangle that = (Rectangle) o;
        return (a == that.a && b == that.b) || (a == that.b && b == that.a);
    }

    @Override
    public int hashCode() {
        int min = Math.min(a, b);
        int max = Math.max(a, b);
        return Objects.hash(min, max);
    }
}
