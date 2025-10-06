package test.task01.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mother extends Person {

    private final int age;
    private final List<Child> children = new ArrayList<>();

    public Mother(int id, String name, int age) {
        super(id, name);
        this.age = age;
    }

    public int getAge() { return age; }


    public List<Child> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public void addChild(Child child) {
        if (child == null) return;
        if (!children.contains(child)) {
            children.add(child);
        }
        if (child.getMother() != this) {
            child.setMother(this);
        }
    }


    public boolean hasTwins() {
        for (int i = 0; i < children.size(); i++) {
            Child a = children.get(i);
            LocalDate da = a.getBirthDate();
            for (int j = i + 1; j < children.size(); j++) {
                Child b = children.get(j);
                LocalDate db = b.getBirthDate();
                if (da.equals(db)) return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", age=" + age +
                ", childrenCount=" + children.size() +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
