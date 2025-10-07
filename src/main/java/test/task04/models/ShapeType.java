package test.task04.models;

public enum ShapeType {
    SQUARE("SQUARE", "Kwadrat") {
        @Override public Shape parse(String[] p) {
            if (p.length < 2) return null;
            try { return Shape.createSquare(Integer.parseInt(p[1])); }
            catch (NumberFormatException ex) { return null; }
        }
    },
    CIRCLE("CIRCLE", "Koło") {
        @Override public Shape parse(String[] p) {
            if (p.length < 2) return null;
            try { return Shape.createCircle(Double.parseDouble(p[1])); }
            catch (NumberFormatException ex) { return null; }
        }
    },
    RECTANGLE("RECTANGLE", "Prostokąt") {
        @Override public Shape parse(String[] p) {
            if (p.length < 3) return null;
            try {
                int a = Integer.parseInt(p[1]);
                int b = Integer.parseInt(p[2]);
                return Shape.createRectangle(a, b);
            } catch (NumberFormatException ex) { return null; }
        }
    };

    private final String code, pl;
    ShapeType(String code, String pl) { this.code = code; this.pl = pl; }
    public String code() { return code; }
    public String pl() { return pl; }

    public static ShapeType fromCode(String code) {
        String c = code.trim().toUpperCase();
        for (ShapeType t : values()) if (t.code.equals(c)) return t;
        throw new IllegalArgumentException("Nieznany typ figury: " + code);
    }

    public abstract Shape parse(String[] parts);
}
