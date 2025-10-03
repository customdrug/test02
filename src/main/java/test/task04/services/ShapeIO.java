package test.task04.services;

import test.task04.models.Shape;
import test.task04.models.ShapeType;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public final class ShapeIO {

    private ShapeIO() {}

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
                if (line.isEmpty() || line.startsWith("#")) continue;

                String[] parts = line.split(";");
                ShapeType type = ShapeType.fromCode(parts[0]);
                Shape shape = type.parse(parts);
                if (shape != null) {
                    out.add(shape);
                }
            }
        }
        return out;
    }
}
