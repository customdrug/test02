package test.task02.services;

import test.task02.models.Doctor;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class DoctorLoader {

    private DoctorLoader() {}

    public static Map<Integer, Doctor> load(Path path) {
        Map<Integer, Doctor> byId = new HashMap<>();
        Set<String> usedNips = new HashSet<>();

        try (BufferedReader br = ParsingUtils.openReader(path)) {
            String raw; int lineNo = 0;

            while ((raw = br.readLine()) != null) {
                lineNo++;
                String line = ParsingUtils.clean(raw);
                if (ParsingUtils.isSkippable(line) || ParsingUtils.looksLikeHeader(line)) continue;

                String[] t = ParsingUtils.splitTabsOrLog(line, lineNo, 7, "niepoprawny lekarz");
                if (t == null) continue;

                Integer id     = ParsingUtils.parseIntSafe(t[0]);
                String last    = t[1].trim();
                String first   = t[2].trim();
                String spec    = t[3].trim();
                LocalDate born = ParsingUtils.parseDateSafe(t[4]);
                String nip     = t[5].trim();
                String pesel   = t[6].trim();

                if (id == null || born == null || last.isEmpty() || first.isEmpty()
                        || spec.isEmpty() || nip.isEmpty() || pesel.isEmpty()) {
                    ParsingUtils.logBad(lineNo, line, "niepoprawny lekarz (brak/format pól)");
                    continue;
                }

                if (byId.containsKey(id)) {
                    System.out.println("Duplikat ID lekarza: " + id + " (wiersz " + lineNo + ") – pomijam (bierzemy pierwszego)");
                    continue;
                }

                String nipNorm = ParsingUtils.normalizeNip(nip);
                if (usedNips.contains(nipNorm)) {
                    System.out.println("Duplikat NIP lekarza: " + nip + " (wiersz " + lineNo + ") – pomijam (bierzemy pierwszego)");
                    continue;
                }

                Doctor d = new Doctor(id, last, first, spec, born, nip, pesel);
                byId.put(id, d);
                usedNips.add(nipNorm);
            }
        } catch (IOException ioe) {
            System.out.println("Plik lekarze.txt – błąd odczytu: " + ioe.getMessage());
        }

        return byId;
    }
}
