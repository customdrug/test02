package test.task04.models;

public final class Circle extends Shape {
    private final double r;

    public Circle(double r) {
        this.r = r;
    }

    @Override public double calculatePerimeter() { return 2 * Math.PI * r; }
    @Override public double calculateArea() { return Math.PI * r * r; }

    @Override
    public String descriptionPl() {
        String t = (r == Math.rint(r)) ? String.valueOf((int) r) : String.valueOf(r);
        return "Koło o promieniu " + t + ".";
    }

    @Override public String toStorageLine() { return ShapeType.CIRCLE.code() + ";" + r; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Circle)) return false;
        Circle other = (Circle) o;
        return Double.compare(r, other.r) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(r);
    }

}
