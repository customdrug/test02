package test.task01.models;

import java.time.DayOfWeek;

public enum PolishDay {
    MONDAY(DayOfWeek.MONDAY, "poniedziałek"),
    TUESDAY(DayOfWeek.TUESDAY, "wtorek"),
    WEDNESDAY(DayOfWeek.WEDNESDAY, "środa"),
    THURSDAY(DayOfWeek.THURSDAY, "czwartek"),
    FRIDAY(DayOfWeek.FRIDAY, "piątek"),
    SATURDAY(DayOfWeek.SATURDAY, "sobota"),
    SUNDAY(DayOfWeek.SUNDAY, "niedziela");

    private final DayOfWeek day;
    private final String polishName;

    PolishDay(DayOfWeek day, String polishName) {
        this.day = day;
        this.polishName = polishName;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public String getPolishName() {
        return polishName;
    }

    public static String nameFor(DayOfWeek dow) {
        for (PolishDay pd : values()) {
            if (pd.day == dow) {
                return pd.polishName;
            }
        }
        return dow.toString();
    }
}
