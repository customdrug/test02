package test.task03.models;

public class Student extends Person{

    private final String group;
    private final double scholarship;

    public Student(String name, String lastName, String pesel, String city, String group, double scholarship) {
        super(name, lastName, pesel, city);
        this.group = group;
        this.scholarship = scholarship;
    }

    public String getGroup() {
        return group;
    }

    public double getScholarship() {
        return scholarship;
    }

    @Override
    public double getIncome() {
        return scholarship;
    }

    @Override
    public String toString() {
        return super.toString() + " " + group + " " + scholarship;
    }
}
