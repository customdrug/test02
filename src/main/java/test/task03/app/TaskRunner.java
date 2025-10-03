package test.task03.app;

import test.task03.models.Employee;
import test.task03.models.Person;
import test.task03.models.Student;
import test.task03.services.PersonIOTxt;
import test.task03.services.PersonService;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class TaskRunner {

    public static void main(String[] args) {


        Person[] people = new Person[] {
                new Student("Anna", "Kowalska", "02210112346", "Poznań", "INF1", 1200.0),
                new Employee("Jan", "Nowak", "99010112335", "Warszawa", "Programista", 8500.0),
                new Student("Ola", "Zielińska", "01222998760", "Gdańsk", "BIO2", 1500.0),
                new Employee("Ewa", "Wiśniewska", "82081512342", "Wrocław", "Księgowa", 6500.0)
        };


        System.out.println("=== ANALIZA (in-memory) ===");
        int womenCount = PersonService.countWomen(people);
        System.out.println("(a) Liczba kobiet: " + womenCount);

        Person richest = PersonService.findRichest(people);
        System.out.println("(b) Najbogatsza osoba: " + richest);


        Path out = Path.of("people.txt");
        try {
            PersonIOTxt.save(people, out);
            System.out.println("\nPlik zapisany do: " + out.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Błąd zapisu: " + e.getMessage());
            return;
        }


        List<Person> loaded;
        try {
            loaded = PersonIOTxt.load(out);
            System.out.println("\n=== Wczytane osoby z pliku ===");
            for (Person p : loaded) {
                System.out.println(" - " + p);
            }
        } catch (IOException e) {
            System.err.println("Błąd odczytu: " + e.getMessage());
            return;
        }


        System.out.println("\n=== ANALIZA (from file) ===");
        int womenCountFromFile = PersonService.countWomen(loaded.toArray(new Person[0]));
        System.out.println("(c) Liczba kobiet (po odczycie): " + womenCountFromFile);

        Person richestFromFile = PersonService.findRichest(loaded.toArray(new Person[0]));
        System.out.println("(d) Najbogatsza osoba (po odczycie): " + richestFromFile);
    }
}