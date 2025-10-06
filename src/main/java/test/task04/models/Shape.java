package test.task04.models;

public abstract class Shape {

    private static int NEXT_NO = 0;
    private final int no;

    protected Shape() {
        this.no = ++NEXT_NO;
    }

    public final int getNo() {
        return no;
    }


    public abstract double calculatePerimeter();
    public abstract double calculateArea();
    public abstract String descriptionPl();
    public abstract String toStorageLine();


    public static Shape createSquare(int side) {
        return new Square(side);
    }

    public static Shape createCircle(int radius) {
        return new Circle(radius);
    }

    public static Shape createRectangle(int a, int b) {
        return new Rectangle(a, b);
    }

    public final String toString() {
        return "Figura nr " + no + ": " + descriptionPl();
    }
}