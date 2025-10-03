package test.task01.services;

import test.task01.models.Child;
import test.task01.models.Gender;
import test.task01.models.Mother;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class ChildrenLoader {

    private static final DateTimeFormatter ISO_DATE =
            DateTimeFormatter.ofPattern("uuuu-MM-dd");

    public static List<Child> load(Path childrenFile, Map<Integer, Mother> mothers) throws IOException {
        List<Child> result = new ArrayList<>();

        try (BufferedReader br = ParsingUtils.newReader(childrenFile)) {
            String raw;
            int lineNo = 0;

            while ((raw = br.readLine()) != null) {
                lineNo++;
                if (ParsingUtils.isSkippable(raw)) continue;

                String[] parts = ParsingUtils.splitCols(raw);
                ParsingUtils.ensureExactCols(parts, 7, lineNo, raw);

                Child child = buildChild(parts, lineNo, raw);
                int motherId = ParsingUtils.parseInt(parts[6], "id matki", lineNo, raw);
                Mother mother = getMotherOrThrow(mothers, motherId, lineNo, raw);

                link(child, mother);
                result.add(child);
            }
        }
        return result;
    }

    private static Child buildChild(String[] p, int lineNo, String raw) {
        int id = ParsingUtils.parseInt(p[0], "id dziecka", lineNo, raw);
        Gender gender = parseGender(p[1], lineNo, raw);
        String name = p[2];
        LocalDate birth = parseBirthIso(p[3], lineNo, raw);
        int weight = ParsingUtils.parseInt(p[4], "waga", lineNo, raw);
        int height = ParsingUtils.parseInt(p[5], "wzrost", lineNo, raw);
        return new Child(id, gender, name, birth, weight, height);
    }

    private static Gender parseGender(String token, int lineNo, String raw) {
        if (token == null || token.isEmpty()) {
            throw new IllegalArgumentException("Brak płci w linii " + lineNo + ": " + raw);
        }
        try {
            return Gender.fromCode(token.charAt(0)); // 'c' lub 's'
        } catch (IllegalArgumentException iae) {
            throw new IllegalArgumentException("Nieprawidłowa płeć '" + token + "' w linii " + lineNo + ": " + raw, iae);
        }
    }

    private static LocalDate parseBirthIso(String text, int lineNo, String raw) {
        try {
            return LocalDate.parse(text, ISO_DATE);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Nieprawidłowa data (oczekuję YYYY-MM-DD) '" + text + "' w linii " + lineNo + ": " + raw, e);
        }
    }

    private static Mother getMotherOrThrow(Map<Integer, Mother> mothers, int motherId, int lineNo, String raw) {
        Mother m = mothers.get(motherId);
        if (m == null) {
            throw new IllegalStateException("Brak matki o id=" + motherId + " w linii " + lineNo + ": " + raw);
        }
        return m;
    }

    private static void link(Child child, Mother mother) {
        child.setMother(mother);
    }
}
