package test.task03.services;

import test.task03.models.Employee;
import test.task03.models.Person;
import test.task03.models.Student;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class PersonIOTxt {

    private static final String HEADER = "type name lastName pesel city group/position income";

    private PersonIOTxt() {
    }

    //toString() w klasch modelowych dostosowalem specjalnie aby korzystały w tej metodzie z polimorfizmu a nie instanceOf
    public static void save(Person[] people, Path path) throws IOException {
        if (people == null) return;
        try (BufferedWriter bw = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            bw.write(HEADER);
            bw.newLine();

            for (Person p : people) {
                if (p == null) continue;
                bw.write(p.toString());
                bw.newLine();
            }
        }
    }

    // metoda dodatkowa(odczytujaca) aby sprawdzić czy z pliku da sie odczytac informacje
    public static List<Person> load(Path path) throws IOException {
        List<Person> result = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            int lineNo = 0;

            while ((line = br.readLine()) != null) {
                lineNo++;
                line = line.trim();
                if (line.isEmpty()) continue;
                if (lineNo == 1 && line.equals(HEADER)) continue;

                Person parsed = parseLine(line);
                if (parsed != null) result.add(parsed);
            }
        }
        return result;
    }

    private static Person parseLine(String line) {
        String[] tok = line.split(" ");
        if (tok.length < 7) return null;

        String type     = tok[0];
        String name     = tok[1];
        String lastName = tok[2];
        String pesel    = tok[3];
        String city     = tok[4];

        try {
            if ("Student".equals(type)) {
                String group = tok[5];
                double scholarship = Double.parseDouble(tok[6]);
                if (scholarship < 0) return null;
                return new Student(name, lastName, pesel, city, group, scholarship);
            } else if ("Employee".equals(type)) {
                String position = tok[5];
                double salary = Double.parseDouble(tok[6]);
                if (salary < 0) return null;
                return new Employee(name, lastName, pesel, city, position, salary);
            }
        } catch (NumberFormatException ex) {
            return null; // income nie jest poprawną liczbą
        }
        return null;
    }
}
