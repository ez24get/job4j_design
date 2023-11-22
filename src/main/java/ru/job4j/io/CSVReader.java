package ru.job4j.io;

import java.io.*;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        List<String> input = new ArrayList<>();
        List<String> headLineOut = new ArrayList<>() ;
        List<String> bodyOut = new ArrayList<>();
        List<Integer> index = new ArrayList<>();
        String[] filterParts = argsName.get("filter").split(",");
        List<String> filters = new ArrayList<>(Arrays.asList(filterParts));
        try (var scanner = new Scanner(new FileInputStream(argsName.get("path")))
                .useDelimiter(System.lineSeparator())) {
            while (scanner.hasNext()) {
                input.add(scanner.next());
            }
        }
        List<String> headLine = new ArrayList<>(List.of(input.get(0).split(argsName.get("delimiter"))));
        for (String filter : filters) {
            for (int j = 0; j < headLine.size(); j++) {
                String line = headLine.get(j);
                if (filter.contentEquals(line)) {
                    headLineOut.add(line);
                    index.add(j);
                }
            }
        }
        for (int i = 1; i < input.size(); i++) {
            String[] bodyPart = input.get(i).split(argsName.get("delimiter"));
            for (int j = 0; j < index.size(); j++) {
                if (j < index.size() - 1) {
                    bodyOut.add(bodyPart[index.get(j)] + argsName.get("delimiter"));
                } else {
                    bodyOut.add(bodyPart[index.get(j)] + System.lineSeparator());
                }
            }
        }
        String head = String.join(argsName.get("delimiter"), headLineOut) + System.lineSeparator();
        String body = String.join("", bodyOut);
        String outFinal = head + body;
        try (PrintStream stream = new PrintStream(new FileOutputStream(argsName.get("out")))) {
            stream.print(outFinal);
        }
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        File file = new File(argsName.get("path"));
        File outFile = new File(argsName.get("out"));
        if (!file.exists()) {
            throw new NoSuchElementException("Input file does not exist.");
        }
        if (!file.isFile()) {
            throw new InvalidObjectException("Input object is not a file");
        }
        if (!outFile.exists()) {
            throw new NoSuchElementException("Output file does not exist.");
        }
        if (!outFile.isFile()) {
            throw new InvalidObjectException("Output object is not a file");
        }
        handle(argsName);
    }
}