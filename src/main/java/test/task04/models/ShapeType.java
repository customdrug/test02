package test.task04.models;

public enum ShapeType {
    SQUARE("SQUARE", "Kwadrat"),
    CIRCLE("CIRCLE", "Koło"),
    RECTANGLE("RECTANGLE", "Prostokat");

    private final String code;
    private final String pl;

    ShapeType(String code, String pl) {
        this.code = code;
        this.pl = pl;
    }

    public String code() {
        return code;
    }

    public String pl() {
        return pl;
    }

    public static ShapeType fromCode(String code) {
        String c = code.trim().toUpperCase();
        for (ShapeType t : values()) {
            if (t.code.equals(c)) {
                return t;
            }
        }
        throw new IllegalArgumentException("Nieznany typ figury: " + code);
    }
}
