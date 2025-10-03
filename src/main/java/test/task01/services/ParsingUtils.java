package test.task01.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public final class ParsingUtils {
    private static final Charset WINDOWS_1250 = Charset.forName("windows-1250");

    private ParsingUtils() {}


    public static boolean isSkippable(String raw) {
        if (raw == null) return true;
        String line = raw.trim();
        return line.isEmpty() || line.startsWith("#");
    }


    public static String[] splitCols(String raw) {
        return raw.trim().split("\\s+");
    }


    public static void ensureExactCols(String[] parts, int expected, int lineNo, String raw) {
        if (parts.length != expected) {
            throw new IllegalArgumentException(
                    "Nieprawidłowa liczba kolumn (mam " + parts.length + ", oczekuję " + expected + ") w linii " + lineNo + ": " + raw
            );
        }
    }


    public static int parseInt(String token, String field, int lineNo, String raw) {
        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException(
                    "Nieprawidłowa liczba w polu '" + field + "' w linii " + lineNo + ": " + raw, nfe
            );
        }
    }


    public static BufferedReader newReader(Path path) throws IOException {
        return Files.newBufferedReader(path, WINDOWS_1250);
    }
}
