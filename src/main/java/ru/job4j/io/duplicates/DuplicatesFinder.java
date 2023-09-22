package ru.job4j.io.duplicates;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.function.Predicate;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        Path start = Paths.get(".");
        search(start, p -> p.equals(new FileProperty(1200, "my_photo.jpeg"))).forEach(System.out::println);
    }

    public static Set<Path> search(Path root, Predicate<FileProperty> condition) throws IOException {
        DuplicatesVisitor visitor = new DuplicatesVisitor(condition);
        Files.walkFileTree(root, visitor);
        return visitor.getPathSet();
    }
}