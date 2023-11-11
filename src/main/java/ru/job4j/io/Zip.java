package ru.job4j.io;

import java.io.*;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(String.valueOf(path)));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private List<Path> getSources(String[] args) throws IOException {
        ArgsName ext = ArgsName.of(args);
        return new ArrayList<>(Search.search(Path.of(ext.get("d")),
                path -> !path.toFile().getName().endsWith(ext.get("e"))));
    }

    private void checkArgs(String[] args) throws NotDirectoryException, DataFormatException {
        ArgsName jv = ArgsName.of(args);
        File directory = new File(jv.get("d"));
        if (!directory.exists()) {
            throw new NoSuchElementException("Directory does not exist.");
        }
        if (!directory.isDirectory()) {
            throw new NotDirectoryException("Directory is a file");
        }
        if (!jv.get("e").contentEquals(".class")) {
            throw new DataFormatException("Argument \"-e\" must contain only \".class\" format");
        }
        if (!jv.get("o").endsWith(".zip")) {
            throw new DataFormatException("Argument \"-o\" must contain only \".zip\" format");
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

    public static void main(String[] args) throws IOException, DataFormatException {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        ArgsName argsName = ArgsName.of(args);
        Zip zip2 = new Zip();
        zip2.checkArgs(args);
        zip2.packFiles(zip2.getSources(args), new File(argsName.get("o")));
    }
}