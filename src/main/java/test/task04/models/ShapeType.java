package test.task04.models;

public enum ShapeType {
    SQUARE("SQUARE", "Kwadrat") {
        @Override
        public Shape parse(String[] p) {
            if (p.length < 2) return null;
            try {
                int a = Integer.parseInt(p[1]);
                return new Square(a);
            } catch (NumberFormatException ex) {
                return null;
            }
        }
    },
    CIRCLE("CIRCLE", "Koło") {
        @Override
        public Shape parse(String[] p) {
            if (p.length < 2) return null;
            try {
                double r = Double.parseDouble(p[1]);
                return new Circle(r);
            } catch (NumberFormatException ex) {
                return null;
            }
        }
    },
    RECTANGLE("RECTANGLE", "Prostokąt") {
        @Override
        public Shape parse(String[] p) {
            if (p.length < 3) return null;
            try {
                int a = Integer.parseInt(p[1]);
                int b = Integer.parseInt(p[2]);
                return new Rectangle(a, b);
            } catch (NumberFormatException ex) {
                return null;
            }
        }
    };

    private final String code;
    private final String pl;

    ShapeType(String code, String pl) {
        this.code = code;
        this.pl = pl;
    }

    public String code() { return code; }
    public String pl()   { return pl; }

    public static ShapeType fromCode(String code) {
        String c = code.trim().toUpperCase();
        for (ShapeType t : values()) {
            if (t.code.equals(c)) return t;
        }
        throw new IllegalArgumentException("Nieznany typ figury: " + code);
    }

    public abstract Shape parse(String[] parts);
}
