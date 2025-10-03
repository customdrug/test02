package test.task02.services;

import test.task02.models.Doctor;
import test.task02.models.Patient;
import test.task02.models.Visit;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class VisitLoader {

    private VisitLoader() {}

    public static List<Visit> load(Path path, List<Doctor> doctors, List<Patient> patients) {
        List<Visit> visits = new ArrayList<>();

        try (BufferedReader br = ParsingUtils.openReader(path)) {
            String raw; int lineNo = 0;

            while ((raw = br.readLine()) != null) {
                lineNo++;
                String line = ParsingUtils.clean(raw);
                if (ParsingUtils.isSkippable(line) || ParsingUtils.looksLikeHeader(line)) continue;

                String[] t = ParsingUtils.splitTabsOrLog(line, lineNo, 3, "niepoprawna wizyta");
                if (t == null) continue;

                Integer docId = ParsingUtils.parseIntSafe(t[0]);
                Integer patId = ParsingUtils.parseIntSafe(t[1]);
                LocalDate date = ParsingUtils.parseDateSafe(t[2]);

                if (docId == null || patId == null || date == null) {
                    ParsingUtils.logBad(lineNo, line, "niepoprawna wizyta (ID/data)");
                    continue;
                }

                Doctor d = findDoctorById(doctors, docId);
                if (d == null) {
                    System.out.println("Wiersz " + lineNo + " – brak lekarza o ID=" + docId + " (pomijam)");
                    continue;
                }
                Patient p = findPatientById(patients, patId);
                if (p == null) {
                    System.out.println("Wiersz " + lineNo + " – brak pacjenta o ID=" + patId + " (pomijam)");
                    continue;
                }

                if (d.getBirthDate() != null && date.isBefore(d.getBirthDate())) {
                    System.out.println("Wiersz " + lineNo + " – data wizyty (" + date + ") przed urodzeniem lekarza (" + d.getBirthDate() + "), pomijam");
                    continue;
                }
                if (p.getBirthDate() != null && date.isBefore(p.getBirthDate())) {
                    System.out.println("Wiersz " + lineNo + " – data wizyty (" + date + ") przed urodzeniem pacjenta (" + p.getBirthDate() + "), pomijam");
                    continue;
                }
                if (date.isAfter(LocalDate.now())) {
                    System.out.println("Wiersz " + lineNo + " – data wizyty w przyszłości (" + date + "), pomijam");
                    continue;
                }

                Visit v = new Visit(d, p, date);
                d.addVisit(v);
                p.addVisit(v);

                visits.add(v);
            }
        } catch (IOException ioe) {
            System.out.println("Plik wizyty.txt – błąd odczytu: " + ioe.getMessage());
        }

        return visits;
    }

    private static Doctor findDoctorById(List<Doctor> doctors, int id) {
        for (Doctor d : doctors)
            if (d != null && d.getId() == id)
                return d;
        return null;
    }

    private static Patient findPatientById(List<Patient> patients, int id) {
        for (Patient p : patients)
            if (p != null && p.getId() == id)
                return p;
        return null;
    }
}
