package test.task02.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Doctor extends Person {
    private final String specialty;
    private final String nip;
    private final List<Visit> visits = new ArrayList<>();

    public Doctor(int id, String lastName, String firstName,
                  String specialty, LocalDate birthDate,
                  String nip, String pesel) {
        super(id, lastName, firstName, pesel, birthDate);
        this.specialty = specialty;
        this.nip = nip;
    }

    public String getSpecialty() { return specialty; }
    public String getNip() { return nip; }

    public void addVisit(Visit v) {
        visits.add(v);
    }

    public List<Visit> getVisits() { return visits; }

    public Set<Patient> getUniquePatients() {
        Set<Patient> unique = new HashSet<>();
        for (Visit v : visits) {
            unique.add(v.getPatient());
        }
        return unique;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", specialty='" + specialty + '\'' +
                ", nip='" + nip + '\'' +
                ", visits=" + visits.size() +
                ", uniquePatients=" + getUniquePatients().size() +
                '}';
    }
}
