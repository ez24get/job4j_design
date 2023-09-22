package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.function.Predicate;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Predicate<FileProperty> condition;
    private List<Path> paths = new ArrayList<>();
    private Set<Path> pathSet = new HashSet<>();

    public DuplicatesVisitor(Predicate<FileProperty> condition) {
        this.condition = condition;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        boolean isFile = Files.isRegularFile(file);
        FileProperty property = new FileProperty(attrs.size(), file.getFileName().toString());
        if (isFile && condition.test(property)) {
            paths.add(file);
        }
        return super.visitFile(file, attrs);
    }

    public Set<Path> getPathSet() {
        pathSet.addAll(paths);
        return pathSet;
    }
}