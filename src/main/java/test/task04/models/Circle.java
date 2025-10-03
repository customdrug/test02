package test.task04.models;


public class Circle extends BaseFigure implements Shape {

    private final double r;

    public Circle(double r) {
        if (r <= 0) {
            throw new IllegalArgumentException("Radius must be > 0");
        }
        this.r = r;
    }

    public double getR() {
        return r;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * r;
    }

    @Override
    public double calculateArea() {
        return Math.PI * r * r;
    }

    @Override
    public String descriptionPl() {
        String rTxt = (r == Math.rint(r)) ? String.valueOf((int) r) : String.valueOf(r);
        return "Koło o promienu " + rTxt + ".";
    }

    @Override
    public String toStorageLine() {
        return ShapeType.CIRCLE.code() + ";" + r;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Circle)) {
            return false;
        }
        Circle other = (Circle) o;
        return Double.compare(r, other.r) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(r);
    }
}
