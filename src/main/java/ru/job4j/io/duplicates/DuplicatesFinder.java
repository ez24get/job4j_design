package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        Path start = Path.of(".");
        DuplicatesVisitor visitor = new DuplicatesVisitor();
        Files.walkFileTree(start, visitor);
        Map<FileProperty, List<Path>> duplicates = visitor.getDuplicates();
        for (Map.Entry<FileProperty, List<Path>> entry : duplicates.entrySet()) {
            if (entry.getValue().size() > 1) {
                System.out.println(entry.getKey().getName() + " " + entry.getKey().getSize());
                for (Path path : entry.getValue()) {
                    System.out.println("  " + path.toAbsolutePath());
                }
            }
        }
    }
}
