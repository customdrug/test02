package test.task03.services;

import test.task03.models.Gender;
import test.task03.models.Person;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class PersonService {

    private PersonService() {
    }

    public static Person findRichest(Person[] people) {
        if (people == null || people.length == 0) return null;
        Person richest = null;
        double max = Double.NEGATIVE_INFINITY;

        for (Person p : people) {
            if (p == null) continue;
            double income = p.getIncome();
            if (richest == null || income > max) {
                richest = p;
                max = income;
            }
        }
        return richest;
    }

    public static int countWomen(Person[] people) {
        if (people == null) return 0;
        int count = 0;
        for (Person p : people) {
            if (p == null) continue;
            if (p.getGender() == Gender.FEMALE) count++;
        }
        return count;
    }
}
