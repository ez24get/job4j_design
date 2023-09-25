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
        List<Path> paths = duplicates.getOrDefault(property, new ArrayList<>());
        paths.add(file);
        duplicates.put(property, paths);
        return super.visitFile(file, attrs);
    }

    public Map<FileProperty, List<Path>> getDuplicates() {
        return duplicates;
    }
}