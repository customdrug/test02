package test.task03.models;

public class Employee extends Person {

    private String position;
    private double salary;

    public Employee(String name, String lastName, String pesel, String city, String position, double salary) {
        super(name, lastName, pesel, city);
        this.position = position;
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public double getIncome() {
        return salary;
    }

    @Override
    public String toString() {
        return super.toString() + " " + position + " " + salary;
    }
}
