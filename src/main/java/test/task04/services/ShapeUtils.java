package test.task04.services;

import test.task04.models.Shape;

import java.util.List;

public final class ShapeUtils {

    private ShapeUtils() {
    }

    public static Shape withMaxPerimeter(List<Shape> shapes) {
        if (shapes == null || shapes.isEmpty()) {
            return null;
        }
        Shape best = shapes.get(0);
        for (int i = 1; i < shapes.size(); i++) {
            Shape s = shapes.get(i);
            if (s.calculatePerimeter() > best.calculatePerimeter()) {
                best = s;
            }
        }
        return best;
    }

    public static Shape withMaxArea(List<Shape> shapes) {
        if (shapes == null || shapes.isEmpty()) {
            return null;
        }
        Shape best = shapes.get(0);
        for (int i = 1; i < shapes.size(); i++) {
            Shape s = shapes.get(i);
            if (s.calculateArea() > best.calculateArea()) {
                best = s;
            }
        }
        return best;
    }
}
