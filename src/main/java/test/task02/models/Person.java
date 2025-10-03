package test.task02.models;

import java.time.LocalDate;
import java.util.Objects;

abstract class Person {
    private final int id;
    private final String lastName;
    private final String firstName;
    private final String pesel;
    private final LocalDate birthDate;

    protected Person(int id, String lastName, String firstName, String pesel, LocalDate birthDate) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.pesel = pesel;
        this.birthDate = birthDate;
    }

    public int getId() { return id; }
    public String getLastName() { return lastName; }
    public String getFirstName() { return firstName; }
    public String getPesel() { return pesel; }
    public LocalDate getBirthDate() { return birthDate; }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id;
    }

    @Override
    public final int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "{id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", pesel='" + pesel + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
