package test.task01.services;

import test.task01.models.Mother;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public final class MothersLoader {

    public static Map<Integer, Mother> load(Path momsFile) throws IOException {
        Map<Integer, Mother> byId = new HashMap<>();

        try (BufferedReader br = ParsingUtils.newReader(momsFile)) {
            String raw;
            int lineNo = 0;

            while ((raw = br.readLine()) != null) {
                lineNo++;
                if (ParsingUtils.isSkippable(raw)) continue;

                String[] parts = ParsingUtils.splitCols(raw);
                ParsingUtils.ensureExactCols(parts, 3, lineNo, raw);

                int id   = ParsingUtils.parseInt(parts[0], "id matki", lineNo, raw);
                String name = parts[1];
                int age  = ParsingUtils.parseInt(parts[2], "wiek", lineNo, raw);

                Mother m = new Mother(id, name, age);
                if (byId.putIfAbsent(id, m) != null) {
                    throw new IllegalStateException("Duplikat id matki (" + id + ") w linii " + lineNo + ": " + raw);
                }
            }
        }
        return byId;
    }
}
