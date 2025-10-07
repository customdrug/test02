package test.task04.app;

import test.task04.models.*;
import test.task04.services.ShapeIO;
import test.task04.services.ShapeUtils;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class TaskRunner {

    public static void main(String[] args) throws Exception {
        List<Shape> shapes = Arrays.asList(
                Shape.createSquare(10),
                Shape.createCircle(20),
                Shape.createRectangle(10, 20)
        );

        for (Shape s : shapes) {
            System.out.println(s);
        }

        Shape maxPer = ShapeUtils.withMaxPerimeter(shapes);
        Shape maxArea = ShapeUtils.withMaxArea(shapes);

        System.out.println("maxPer = " + maxPer);
        System.out.println("maxArea = " + maxArea);

        System.out.println(shapes.contains(new Square(10)));

        Path file = Path.of("figury.txt");
        ShapeIO.write(shapes, file);
        List<Shape> readBack = ShapeIO.read(file);
        for (Shape s : readBack) {
            System.out.println(s);
        }

        Circle testCircle = new Circle(5);
        System.out.println(testCircle);
    }
}
