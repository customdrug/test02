package test.task04.models;

public abstract class Shape {

    private int id;

    public final int getId() { return id; }

    private void setId(int id) {
        this.id = id;
    }

    public static Square createSquare(int side) {
        if (side <= 0)
            throw new IllegalArgumentException("Bok nie może być mniejszy ani równy zero");
        Square s = new Square(side);
        ((Shape)s).setId(IdGenerator.nextId());
        return s;
    }
    public static Circle createCircle(double radius) {
        if (radius <= 0)
            throw new IllegalArgumentException("Promień nie może być mniejszy ani równy zero");
        Circle c = new Circle(radius);
        ((Shape)c).setId(IdGenerator.nextId());
        return c;
    }
    public static Rectangle createRectangle(int a, int b) {
        if (a <= 0 || b <= 0)
            throw new IllegalArgumentException("Żaden z boków nie może być mniejszy ani równy zero");
        Rectangle r = new Rectangle(a, b);
        ((Shape)r).setId(IdGenerator.nextId());
        return r;
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