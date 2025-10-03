package test.task02.services;

import test.task02.models.Patient;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public final class PatientLoader {

    private PatientLoader() {}

    public static Map<Integer, Patient> load(Path path) {
        Map<Integer, Patient> byId = new HashMap<>();

        try (BufferedReader br = ParsingUtils.openReader(path)) {
            String raw; int lineNo = 0;

            while ((raw = br.readLine()) != null) {
                lineNo++;
                String line = ParsingUtils.clean(raw);
                if (ParsingUtils.isSkippable(line) || ParsingUtils.looksLikeHeader(line)) continue;

                String[] t = ParsingUtils.splitTabsOrLog(line, lineNo, 5, "niepoprawny pacjent");
                if (t == null) continue;

                Integer id     = ParsingUtils.parseIntSafe(t[0]);
                String last    = t[1].trim();
                String first   = t[2].trim();
                String pesel   = t[3].trim();
                LocalDate born = ParsingUtils.parseDateSafe(t[4]);

                if (id == null || born == null || last.isEmpty() || first.isEmpty() || pesel.isEmpty()) {
                    ParsingUtils.logBad(lineNo, line, "niepoprawny pacjent (brak/format pól)");
                    continue;
                }

                if (byId.containsKey(id)) {
                    System.out.println("Duplikat ID pacjenta: " + id + " (wiersz " + lineNo + ") – pomijam (bierzemy pierwszego)");
                    continue;
                }

                Patient p = new Patient(id, last, first, pesel, born);
                byId.put(id, p);
            }
        } catch (IOException ioe) {
            System.out.println("Plik pacjenci.txt – błąd odczytu: " + ioe.getMessage());
        }

        return byId;
    }
}
