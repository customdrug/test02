package test.task04.models;

public abstract class BaseFigure implements Shape {

    private static int NEXT_NO = 0;
    private int no = 0;


    private void ensureNumber() {
        if (no == 0) {
            no = ++NEXT_NO;
        }
    }

    @Override
    public final String toString() {
        ensureNumber();
        return "Figura nr " + no + ": " + descriptionPl();
    }
}
