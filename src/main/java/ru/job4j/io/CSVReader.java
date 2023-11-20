package ru.job4j.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;

public class CSVReader {

    public static void handle(ArgsName argsName) throws Exception {
        List<String> input = new ArrayList<>();
        List<String> headLineOut = new ArrayList<>();
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
        for (int i = 0; i < headLine.size(); i++) {
            String line = headLine.get(i);
            for (String filter : filters) {
                if (filter.contentEquals(line)) {
                    headLineOut.add(line);
                    index.add(i);
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
        handle(argsName);
    }
}