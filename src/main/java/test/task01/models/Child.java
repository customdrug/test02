package test.task01.models;

import java.time.LocalDate;
import java.util.Objects;

public class Child extends Person {

    private final Gender gender;
    private final LocalDate birthDate;
    private final int weightGrams;
    private final int heightCm;

    private Mother mother;

    public Child(int id,
                 Gender gender,
                 String name,
                 LocalDate birthDate,
                 int weightGrams,
                 int heightCm) {
        super(id, name);
        this.gender = Objects.requireNonNull(gender, "gender");
        this.birthDate = birthDate;
        this.weightGrams = weightGrams;
        this.heightCm = heightCm;
    }

    public Gender getGender() {
        return gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public int getWeightGrams() {
        return weightGrams;
    }

    public int getHeightCm() {
        return heightCm;
    }

    public Mother getMother() {
        return mother;
    }

    public void setMother(Mother mother) {
        if (this.mother == mother) return;
        this.mother = mother;
        if (mother != null) {
            mother.addChild(this);
        }
    }

    @Override
    public String toString() {
        return super.toString() +
                ", gender=" + gender +
                ", birthDate=" + birthDate +
                ", weightGrams=" + weightGrams +
                ", heightCm=" + heightCm +
                ", mother=" + mother +
                '}';
    }
}