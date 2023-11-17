package ru.job4j.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;

public class CSVReader {

    private static List<String> input = new ArrayList<>();
    private static List<String> headLine = new ArrayList<>();
    private static List<String> headLineOut = new ArrayList<>();
    private static List<String> bodyOut = new ArrayList<>();
    private static List<String> filters = new ArrayList<>();
    private static List<Integer> index = new ArrayList<>();
    private static List<String> output = new ArrayList<>();

    public static void handle(ArgsName argsName) throws Exception {
        getFilters(argsName);
        var scanner = new Scanner(new FileInputStream(argsName.get("path")))
                .useDelimiter(System.lineSeparator());
        while (scanner.hasNext()) {
            input.add(scanner.next());
        }
        headLine.addAll(List.of(input.get(0).split(";")));
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
            String[] bodyPart = input.get(i).split(";");
            for (int j = 0; j < index.size(); j++) {
                if (j < index.size() - 1) {
                    bodyOut.add(bodyPart[index.get(j)]);
                } else {
                    bodyOut.add(bodyPart[index.get(j)] + System.lineSeparator());
                }
            }
        }
        try (PrintStream stream = new PrintStream(new FileOutputStream(argsName.get("out")))) {
            stream.print(combineHeadAndBody());
        }
    }

    private static void getFilters(ArgsName argsName) {
        String[] filterParts = argsName.get("filter").split(";");
        filters.addAll(Arrays.asList(filterParts));
    }

    private static String combineHeadAndBody() {
        String head = String.join(";", headLineOut) + System.lineSeparator();
        String body = String.join(";", bodyOut);
        return head + body;
    }

    public static void main(String[] args) throws Exception {
        /* здесь добавьте валидацию принятых параметров*/
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}