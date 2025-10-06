package test.task02.services;

import test.task02.models.Doctor;
import test.task02.models.Patient;
import test.task02.models.Visit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DataAnalyzer {
    private final List<Doctor> doctors;
    private final List<Patient> patients;
    private final List<Visit> visits;

    public DataAnalyzer(List<Doctor> doctors, List<Patient> patients, List<Visit> visits) {
        this.doctors = doctors;
        this.patients = patients;
        this.visits = visits;
    }


    private int countUniquePatients(Doctor d) {
        Set<Patient> set = new HashSet<>();
        for (Visit v : d.getVisits()) {
            set.add(v.getPatient());
        }
        return set.size();
    }


    private int countUniqueDoctors(Patient p) {
        Set<Doctor> set = new HashSet<>();
        for (Visit v : p.getVisits()) {
            set.add(v.getDoctor());
        }
        return set.size();
    }


    public Doctor doctorWithMostVisits() {
        Doctor best = null;
        int bestCount = -1;
        for (Doctor d : doctors) {
            int c = d.getVisits().size();
            if (c > bestCount) {
                bestCount = c;
                best = d;
            }
        }
        return best;
    }

    public Patient patientWithMostVisits() {
        Patient best = null;
        int bestCount = -1;
        for (Patient p : patients) {
            int c = p.getVisits().size();
            if (c > bestCount) {
                bestCount = c;
                best = p;
            }
        }
        return best;
    }


    public String mostPopularSpecialty() {
        String bestSpec = null;
        int bestCount = -1;

        for (Doctor d1 : doctors) {
            String spec = d1.getSpecialty();
            int count = 0;
            for (Doctor d2 : doctors) {
                if (spec.equals(d2.getSpecialty())) {
                    count += d2.getVisits().size();
                }
            }
            if (count > bestCount) {
                bestCount = count;
                bestSpec = spec;
            }
        }
        return bestSpec;
    }

    public int yearWithMostVisits() {
        int bestYear = -1;
        int bestCount = -1;

        for (Visit ref : visits) {
            int year = ref.getDate().getYear();
            int count = 0;
            for (Visit v : visits) {
                if (v.getDate().getYear() == year) {
                    count++;
                }
            }
            if (count > bestCount) {
                bestCount = count;
                bestYear = year;
            }
        }
        return bestYear;
    }

    public List<Doctor> top5OldestDoctors() {
        List<Doctor> pool = new ArrayList<>(doctors);
        List<Doctor> result = new ArrayList<>();
        for (int i = 0; i < 5 && !pool.isEmpty(); i++) {
            Doctor oldest = null;
            for (Doctor d : pool) {
                if (oldest == null || d.getBirthDate().isBefore(oldest.getBirthDate())) {
                    oldest = d;
                }
            }
            result.add(oldest);
            pool.remove(oldest);
        }
        return result;
    }

    public List<Doctor> top5DoctorsByVisits() {
        List<Doctor> pool = new ArrayList<>(doctors);
        List<Doctor> result = new ArrayList<>();
        for (int i = 0; i < 5 && !pool.isEmpty(); i++) {
            Doctor best = null;
            for (Doctor d : pool) {
                if (best == null || d.getVisits().size() > best.getVisits().size()) {
                    best = d;
                }
            }
            result.add(best);
            pool.remove(best);
        }
        return result;
    }


    public List<Patient> patientsWithAtLeastNDoctors(int n) {
        List<Patient> out = new ArrayList<>();
        for (Patient p : patients) {
            if (countUniqueDoctors(p) >= n) {
                out.add(p);
            }
        }
        return out;
    }

    public List<Doctor> doctorsWithExactlyOnePatient() {
        List<Doctor> out = new ArrayList<>();
        for (Doctor d : doctors) {
            if (countUniquePatients(d) == 1) {
                out.add(d);
            }
        }
        return out;
    }

    public int maxUniqueDoctorsPerPatient() {
        int best = 0;
        for (Patient p : patients) {
            int count = countUniqueDoctors(p);
            if (count > best) best = count;
        }
        return best;
    }


    public int minUniquePatientsPerDoctor() {
        if (doctors.isEmpty()) return 0;
        int min = Integer.MAX_VALUE;
        for (Doctor d : doctors) {
            int count = countUniquePatients(d);
            if (count < min) min = count;
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
