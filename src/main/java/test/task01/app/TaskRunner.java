package test.task01.app;

import test.task01.models.*;
import test.task01.services.ChildrenLoader;
import test.task01.services.DataAnalyzer;
import test.task01.services.MothersLoader;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TaskRunner {

    public static void main(String[] args) {

        List<Mother> mothers;
        List<Child> children;

        try {
            Path momsPath = Path.of("src/main/resources/mamy.txt");
            Path kidsPath = Path.of("src/main/resources/noworodki.txt");

            Map<Integer, Mother> mothersById = MothersLoader.load(momsPath);
            mothers = new ArrayList<>(mothersById.values());
            children = ChildrenLoader.load(kidsPath, mothersById);

        } catch (IOException | IllegalArgumentException | IllegalStateException e) {
            System.err.println("Błąd danych/wejścia: " + e.getMessage());
            return;
        }

        System.out.println("\n=== ANALIZA (a) ===");
        DataAnalyzer da = new DataAnalyzer(children, mothers);

        Child boy = da.tallestByGender(Gender.SON);
        if (boy != null) {
            System.out.println("(a) Najwyższy chłopiec: " + boy.getName()
                    + " (" + boy.getHeightCm() + " cm)");
        } else {
            System.out.println("(a) Brak chłopców w danych.");
        }

        Child girl = da.tallestByGender(Gender.DAUGHTER);
        if (girl != null) {
            System.out.println("(a) Najwyższa dziewczynka: " + girl.getName()
                    + " (" + girl.getHeightCm() + " cm)");
        } else {
            System.out.println("(a) Brak dziewczynek w danych.");
        }

        System.out.println("\n=== ANALIZA (b) ===");
        DayCount dc = da.busiestBirthDayOfTheWeek();
        System.out.println("(b) Dzień z największą liczbą urodzeń: "
                + (dc != null ? PolishDay.nameFor(dc.getDay()) + " (" + dc.getCount() + ")" : "-"));

        System.out.println("\n=== ANALIZA (c) ===");
        List<String> moms = da.youngMomsWithHeavyBabies();
        System.out.println("(c) Mamy <25 z dziećmi >4000 g: "
                + (moms.isEmpty() ? "-" : String.join(", ", moms)));

        System.out.println("\n=== ANALIZA (d) ===");
        System.out.println("Córki posiadające te same imiona co mamy");
        List<Child> daughters = da.daughtersSharingMothersName();
        if (daughters.isEmpty()) {
            System.out.println("(d) Brak dziewczynek, które mają imię jak mama.");
        } else {
            for (Child c : daughters) {
                System.out.println("(d) " + c.getName() + " — " + c.getBirthDate());
            }
        }

        System.out.println("\n=== ANALIZA (e) ===");
        List<Mother> twinMoms = da.mothersOfTwins();
        if (twinMoms.isEmpty()) {
            System.out.println("(e) Brak mam z bliźniętami.");
        } else {
            System.out.println("(e) Matki, które urodziły bliźnięta:");
            for (Mother m : twinMoms) {
                System.out.println("    " + m.getName());
            }
        }

        Child testChild = new Child(50, Gender.SON, "Jimmy", LocalDate.EPOCH, 200, 10);
        System.out.println("testChild = " + testChild);
    }
}
