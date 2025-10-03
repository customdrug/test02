package test.task01.models;

import java.time.DayOfWeek;
import java.util.Objects;

public class DayCount {
    private final DayOfWeek day;
    private final int count;

    public DayCount(DayOfWeek day, int count) {
        this.day = Objects.requireNonNull(day);
        this.count = count;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "DayCount{day=" + day + ", count=" + count + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DayCount)) return false;
        DayCount that = (DayCount) o;
        return count == that.count && day == that.day;
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, count);
    }
}