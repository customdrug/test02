package test.task01.services;

import test.task01.models.Child;
import test.task01.models.DayCount;
import test.task01.models.Gender;
import test.task01.models.Mother;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

public class DataAnalyzer {

    private final List<Child> children;
    private final List<Mother> mothers;

    public DataAnalyzer(List<Child> children, List<Mother> mothers) {
        this.children = Objects.requireNonNull(children, "children");
        this.mothers = Objects.requireNonNull(mothers, "mothers");
    }

    public Child tallestByGender(Gender gender) {
        Child best = null;
        for (Child c : children) {
            if (c == null || c.getGender() != gender) continue;
            if (best == null || c.getHeightCm() > best.getHeightCm()) {
                best = c;
            }
        }
        return best;
    }

    public DayCount busiestBirthDayOfTheWeek() {
        int[] counts = new int[8];
        boolean any = false;

        for (Child c : children) {
            if (c == null) continue;
            LocalDate d = c.getBirthDate();
            if (d == null) continue;
            counts[d.getDayOfWeek().getValue()]++;
            any = true;
        }
        if (!any) return null;

        int bestCount = -1;
        DayOfWeek bestDay = null;
        for (int i = 1; i <= 7; i++) {
            if (counts[i] > bestCount) {
                bestCount = counts[i];
                bestDay = DayOfWeek.of(i);
            }
        }
        return (bestDay == null) ? null : new DayCount(bestDay, bestCount);
    }

    public List<String> youngMomsWithHeavyBabies() {
        Set<String> names = new HashSet<>();
        for (Child c : children) {
            if (c == null) continue;
            if (c.getWeightGrams() <= 4000) continue;

            Mother m = c.getMother();
            if (m == null) continue;
            if (m.getAge() >= 25) continue;

            String name = m.getName();
            if (name != null && !name.isBlank()) {
                names.add(name);
            }
        }
        List<String> result = new ArrayList<>(names);
        Collections.sort(result);
        return result;
    }

    public List<Child> daughtersSharingMothersName() {
        List<Child> result = new ArrayList<>();
        for (Child c : children) {
            if (c == null) continue;
            if (c.getGender() != Gender.DAUGHTER) continue;
            Mother m = c.getMother();
            if (c.getName().equalsIgnoreCase(m.getName())) {
                result.add(c);
            }
        }
        return result;
    }

    public List<Mother> mothersOfTwins() {
        List<Mother> result = new ArrayList<>();
        for (Mother m : mothers) {
            if (m != null && m.hasTwins()) {
                result.add(m);
            }
        }
        return result;
    }
}
