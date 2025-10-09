package test.task04.models;

public abstract class Shape {

    private int id;

    public final int getId() { return id; }

    private void setId(int id) {
        this.id = id;
    }

    public static Shape createSquare(int side) {
        if (side <= 0)
            throw new IllegalArgumentException("Bok nie może być mniejszy ani równy zero");
        Shape s = new Square(side);
        s.setId(IdGenerator.nextId());
        return s;
    }
    public static Shape createCircle(double radius) {
        if (radius <= 0)
            throw new IllegalArgumentException("Promień nie może być mniejszy ani równy zero");
        Shape c = new Circle(radius);
        c.setId(IdGenerator.nextId());
        return c;
    }
    public static Shape createRectangle(int a, int b) {
        if (a <= 0 || b <= 0)
            throw new IllegalArgumentException("Żaden z boków nie może być mniejszy ani równy zero");
        Shape r = new Rectangle(a, b);
        r.setId(IdGenerator.nextId());
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