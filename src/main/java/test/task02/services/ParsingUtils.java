package test.task02.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class ParsingUtils {
    private static final Charset WINDOWS_1250 = Charset.forName("windows-1250");

    private ParsingUtils() {}


    public static BufferedReader openReader(Path path) throws IOException {
        try {
            return Files.newBufferedReader(path, WINDOWS_1250);
        } catch (MalformedInputException mie) {
            return Files.newBufferedReader(path, StandardCharsets.UTF_8);
        }
    }


    public static String clean(String s) {
        if (s == null) return "";
        if (!s.isEmpty() && s.charAt(0) == '\uFEFF') s = s.substring(1); // BOM
        return s.trim();
    }


    public static boolean isSkippable(String line) {
        String s = line == null ? "" : line.trim();
        return s.isEmpty() || s.startsWith("#");
    }


    public static boolean looksLikeHeader(String line) {
        String s = line.toLowerCase();
        return s.contains("id") &&
                (s.contains("nazw") || s.contains("imi") || s.contains("pesel") || s.contains("specjal") || s.contains("data"));
    }


    public static String[] splitTabsOrLog(String line, int lineNo, int minCols, String who) {
        String[] t = line.split("\\t+");
        if (t.length < minCols) {
            logBad(lineNo, line, who + ": za mało kolumn (wymagane ≥ " + minCols + " oddzielonych TAB)");
            return null;
        }
        return t;
    }


    public static void logBad(int lineNo, String line, String reason) {
        String prev = (line == null ? "" : line.trim().replaceAll("\\s+", " "));
        if (prev.length() > 80) prev = prev.substring(0, 80) + "…";
        System.out.println("Wiersz " + lineNo + " – " + reason + ": " + prev);
    }


    public static Integer parseIntSafe(String s) {
        try { return Integer.parseInt(s.trim()); } catch (Exception e) { return null; }
    }


    public static LocalDate parseDateSafe(String s) {
        String v = s.trim();
        try { return LocalDate.parse(v); } catch (Exception ignored) {}
        try { return LocalDate.parse(v, DateTimeFormatter.ofPattern("yyyy-M-d")); } catch (Exception ignored) {}
        return null;
    }


    public static String normalizeNip(String nip) {
        return nip == null ? "" : nip.replaceAll("\\D", "");
    }
}
