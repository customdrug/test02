package test.task02.app;

import test.task02.models.Doctor;
import test.task02.models.Patient;
import test.task02.models.Visit;
import test.task02.services.DataAnalyzer;
import test.task02.services.DoctorLoader;
import test.task02.services.PatientLoader;
import test.task02.services.VisitLoader;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TaskRunner {
    public static void main(String[] args) {
        try {

            Map<Integer, Doctor> doctorsById  = DoctorLoader.load(Path.of("src/main/resources/lekarze.txt"));
            Map<Integer, Patient> patientsById = PatientLoader.load(Path.of("src/main/resources/pacjenci.txt"));

            List<Doctor> doctors = new ArrayList<>(doctorsById.values());
            List<Patient> patients = new ArrayList<>(patientsById.values());

            List<Visit> visits = VisitLoader.load(
                    Path.of("src/main/resources/wizyty.txt"),
                    doctors, patients
            );

            DataAnalyzer analyzer = new DataAnalyzer(doctors, patients, visits);


            Doctor mostBusy = analyzer.doctorWithMostVisits();
            if (mostBusy != null) {
                System.out.println("Lekarz z największą liczbą wizyt: "
                        + mostBusy.getFirstName() + " " + mostBusy.getLastName()
                        + " (wizyt: " + mostBusy.getVisits().size() + ")");
            } else {
                System.out.println("Brak danych o lekarzach.");
            }

            Patient mostVisited = analyzer.patientWithMostVisits();
            if (mostVisited != null) {
                System.out.println("Pacjent z największą liczbą wizyt: "
                        + mostVisited.getFirstName() + " " + mostVisited.getLastName()
                        + " (wizyt: " + mostVisited.getVisits().size() + ")");
            } else {
                System.out.println("Brak danych o pacjentach.");
            }

            String hotSpec = analyzer.mostPopularSpecialty();
            System.out.println("Najbardziej oblegana specjalizacja: " + (hotSpec == null ? "brak danych" : hotSpec));

            int bestYear = analyzer.yearWithMostVisits();
            System.out.println(bestYear == -1 ? "Brak danych o wizytach." : "Rok z największą liczbą wizyt: " + bestYear);

            System.out.println("Top 5 najstarszych lekarzy:");
            List<Doctor> oldest5 = analyzer.top5OldestDoctors();
            for (int i = 0; i < oldest5.size(); i++) {
                Doctor d = oldest5.get(i);
                System.out.println((i + 1) + ". " + d.getFirstName() + " " + d.getLastName()
                        + " (ur. " + d.getBirthDate() + ")");
            }

            System.out.println("Top 5 lekarzy z największą liczbą wizyt:");
            List<Doctor> top5ByVisits = analyzer.top5DoctorsByVisits();
            for (int i = 0; i < top5ByVisits.size(); i++) {
                Doctor d = top5ByVisits.get(i);
                System.out.println((i + 1) + ". " + d.getFirstName() + " " + d.getLastName()
                        + " (wizyt: " + d.getVisits().size() + ")");
            }

            System.out.println("Pacjenci, którzy byli u co najmniej 5 różnych lekarzy:");
            List<Patient> p5 = analyzer.patientsWithAtLeastNDoctors(5);
            for (Patient p : p5) {
                System.out.println("- " + p.getFirstName() + " " + p.getLastName()
                        + " (różnych lekarzy: " + p.getUniqueDoctors().size() + ")");
            }

            System.out.println("Lekarze, którzy przyjęli dokładnie jednego pacjenta:");
            List<Doctor> oneOnly = analyzer.doctorsWithExactlyOnePatient();
            for (Doctor d : oneOnly) {
                System.out.println("- " + d.getFirstName() + " " + d.getLastName());
            }

            System.out.println("Maks. liczba różnych lekarzy u pacjenta: "
                    + analyzer.maxUniqueDoctorsPerPatient());
            System.out.println("Minimalna liczba różnych pacjentów u lekarza: "
                    + analyzer.minUniquePatientsPerDoctor());

        } catch (Exception e) {
            System.out.println("Błąd uruchomienia: " + e.getMessage());
        }



    }

}
