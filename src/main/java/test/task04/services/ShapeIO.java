package test.task04.services;

import test.task04.models.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public final class ShapeIO {

    private ShapeIO() {
    }

    public static void write(List<Shape> shapes, Path file) throws IOException {
        try (BufferedWriter bw = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
            for (Shape s : shapes) {
                bw.write(s.toStorageLine());
                bw.newLine();
            }
        }
    }

    public static List<Shape> read(Path file) throws IOException {
        List<Shape> out = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            String raw;
            while ((raw = br.readLine()) != null) {
                String line = raw.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                String[] parts = line.split(";");
                ShapeType type = ShapeType.fromCode(parts[0]);

                switch (type) {
                    case SQUARE: {
                        if (parts.length < 2) {
                            continue;
                        }
                        int a = Integer.parseInt(parts[1]);
                        out.add(new Square(a));
                        break;
                    }
                    case CIRCLE: {
                        if (parts.length < 2) {
                            continue;
                        }
                        double r = Double.parseDouble(parts[1]);
                        out.add(new Circle(r));
                        break;
                    }
                    case RECTANGLE: {
                        if (parts.length < 3) {
                            continue;
                        }
                        int a = Integer.parseInt(parts[1]);
                        int b = Integer.parseInt(parts[2]);
                        out.add(new Rectangle(a, b));
                        break;
                    }
                }
            }
        }
        return out;
    }
}
