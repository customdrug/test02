package test.task03.models;

import java.util.Objects;

public abstract class Person {
    private final String name;
    private final String lastName;
    private final String pesel;
    private final String city;

    protected Person(String name, String lastName, String pesel, String city) {
        this.name = name;
        this.lastName = lastName;
        this.pesel = pesel;
        this.city = city;
    }


    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public String getCity() {
        return city;
    }

    public abstract double getIncome();

    public Gender getGender(){
        if (pesel == null || pesel.length() != 11) return Gender.UNKNOWN;
        for (int i = 0; i < 11; i++) {
            char c = pesel.charAt(i);
            if (c < '0' || c > '9') return Gender.UNKNOWN;
        }
        int genderDigit = pesel.charAt(9) - '0';
        if (genderDigit % 2 == 0) return Gender.FEMALE;
        else return Gender.MALE;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " "
                + name + " " + lastName + " " + pesel + " " + city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person that = (Person) o;
        return Objects.equals(pesel, that.pesel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pesel);
    }
}
