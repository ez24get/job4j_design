package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, List<Path>> duplicates = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty property = new FileProperty(attrs.size(), file.getFileName().toString());
        duplicates.computeIfAbsent(property, k -> new ArrayList<>()).add(file);
        return super.visitFile(file, attrs);
    }

    public void printDuplicates() {
        for (Map.Entry<FileProperty, List<Path>> entry : duplicates.entrySet()) {
            if (entry.getValue().size() > 1) {
                System.out.println(entry.getKey().getName());
                System.out.println(entry.getKey().getSize());
                for (Path path : entry.getValue()) {
                    System.out.println(path.toAbsolutePath());
                }
            }
        }
    }
}