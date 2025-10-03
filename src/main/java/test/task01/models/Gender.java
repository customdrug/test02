package test.task01.models;

public enum Gender {

    DAUGHTER('c'),
    SON('s');

    private final char code;

    Gender(char code) {
        this.code = code;
    }

    public char getCode() {
        return code;
    }

    public static Gender fromCode(char code) {
        return switch (Character.toLowerCase(code)) {
            case 'c' -> DAUGHTER;
            case 's' -> SON;
            default -> throw new IllegalArgumentException("Nieznany kod płci: " + code);
        };
    }
}
