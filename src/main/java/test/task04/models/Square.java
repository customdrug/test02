package test.task04.models;

public final class Square extends Shape {
    private final int a;

    public Square(int a) {
        super(0);
        if (a <= 0) throw new IllegalArgumentException("Bok > 0");
        this.a = a;
    }

    Square(int id, int a) {
        super(id);
        if (a <= 0) throw new IllegalArgumentException("Bok > 0");
        this.a = a;
    }

    @Override public double calculatePerimeter() { return 4.0 * a; }
    @Override public double calculateArea() { return 1.0 * a * a; }
    @Override public String descriptionPl() { return "Kwadrat o boku " + a + "."; }
    @Override public String toStorageLine() { return ShapeType.SQUARE.code() + ";" + a; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Square)) return false;
        Square other = (Square) o;
        return a == other.a;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(a);
    }
}
