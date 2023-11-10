package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        for (Path path : sources) {
            try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
                zip.putNextEntry(new ZipEntry(String.valueOf(path)));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private List<Path> getSources() throws IOException {
        Path start = Paths.get(".");
        return new ArrayList<>(Search.search(start, path -> !path.toFile().getName().endsWith(".class")));
    }

    private void checkArgs(String[] args) {
        ArgsName jv = ArgsName.of(args);
        File directory = new File(jv.get("d"));
        if (!directory.exists()) {
            throw new IllegalArgumentException("Directory does not exist");
        }
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("Directory is a file");
        }
        if (!jv.get("e").contentEquals(".class")) {
            throw new IllegalArgumentException("Argument \"-e\" must contain only \".class\" format");
        }
        if (!jv.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException("Argument \"-o\" must contain only \".zip\" format");
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        ArgsName argsName = ArgsName.of(args);
        Zip zip2 = new Zip();
        zip2.checkArgs(args);
        zip2.packFiles(zip2.getSources(), new File(argsName.get("o")));
    }
}