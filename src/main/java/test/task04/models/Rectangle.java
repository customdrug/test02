package test.task04.models;

public final class Rectangle extends Shape {
    private final int a, b;

    public Rectangle(int a, int b) {
        this.a = a; this.b = b;
    }


    @Override public double calculatePerimeter() { return 2.0 * (a + b); }
    @Override public double calculateArea() { return 1.0 * a * b; }
    @Override public String descriptionPl() { return "Prostokat o bokach " + a + "x" + b + "."; }
    @Override public String toStorageLine() { return ShapeType.RECTANGLE.code() + ";" + a + ";" + b; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rectangle)) return false;
        Rectangle other = (Rectangle) o;
        return a == other.a && b == other.b;
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(a);
        result = 31 * result + Integer.hashCode(b);
        return result;
    }

}
