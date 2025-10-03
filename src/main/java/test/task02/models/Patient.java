package test.task02.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Patient extends Person {
    private final List<Visit> visits = new ArrayList<>();
    private final Set<Doctor> uniqueDoctors = new HashSet<>();

    public Patient(int id, String lastName, String firstName,
                   String pesel, LocalDate birthDate) {
        super(id, lastName, firstName, pesel, birthDate);
    }

    public void addVisit(Visit v) {
        visits.add(v);
        uniqueDoctors.add(v.getDoctor());
    }

    public List<Visit> getVisits() { return visits; }
    public Set<Doctor> getUniqueDoctors() { return uniqueDoctors; }


    @Override
    public String toString() {
        return super.toString() +
                ", visits=" + visits.size() +
                ", uniqueDoctors=" + uniqueDoctors.size() +
                '}';
    }



}
