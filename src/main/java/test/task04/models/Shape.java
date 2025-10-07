package test.task04.models;

public abstract class Shape {

    private final int id;

    protected Shape(int id) { this.id = id; }

    public final int getId() { return id; }

    public static Shape createSquare(int side) {
        return new Square(IdGenerator.nextId(), side);
    }
    public static Shape createCircle(double radius) {
        return new Circle(IdGenerator.nextId(), radius);
    }
    public static Shape createRectangle(int a, int b) {
        return new Rectangle(IdGenerator.nextId(), a, b);
    }

    public abstract double calculatePerimeter();
    public abstract double calculateArea();
    public abstract String descriptionPl();
    public abstract String toStorageLine();

    @Override
    public final String toString() {
        return "Figura nr " + id + ": " + descriptionPl();
    }
}