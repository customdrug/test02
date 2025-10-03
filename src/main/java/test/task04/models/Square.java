package test.task04.models;

import java.util.Objects;

public class Square extends BaseFigure implements Shape {

    private final int a;

    public Square(int a) {
        if (a <= 0) {
            throw new IllegalArgumentException("Side must be > 0");
        }
        this.a = a;
    }

    public int getA() {
        return a;
    }

    @Override
    public double calculatePerimeter() {
        return 4.0 * a;
    }

    @Override
    public double calculateArea() {
        return (double) a * a;
    }

    @Override
    public String descriptionPl() {
        return "Kwadrat o boku " + a + ".";
    }

    @Override
    public String toStorageLine() {
        return ShapeType.SQUARE.code() + ";" + a;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Square)) {
            return false;
        }
        Square other = (Square) o;
        return a == other.a;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a);
    }
}
