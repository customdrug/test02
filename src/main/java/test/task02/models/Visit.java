package test.task02.models;

import java.time.LocalDate;
import java.util.Objects;

public class Visit {
    private final Doctor doctor;
    private final Patient patient;
    private final LocalDate date;

    public Visit(Doctor doctor, Patient patient, LocalDate date) {
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
    }

    public Doctor getDoctor() { return doctor; }
    public Patient getPatient() { return patient; }
    public LocalDate getDate() { return date; }


    @Override
    public String toString() {
        return "Visit{" +
                "doctor=" + doctor +
                ", patient=" + patient +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visit visit = (Visit) o;
        return Objects.equals(doctor, visit.doctor) && Objects.equals(patient, visit.patient) && Objects.equals(date, visit.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctor, patient, date);
    }
}
