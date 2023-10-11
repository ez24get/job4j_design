package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        String keyFinal = key;
        if (!key.startsWith("-")) {
            keyFinal = "-" + key;
        }
        if (!values.containsKey(keyFinal)) {
            throw new IllegalArgumentException("This key: '" + key + "' is missing");
        }
        return values.get(keyFinal);
    }

    private void parse(String[] args) {
        for (String line : args) {
            check(line);
            String[] parts = line.split("=", 2);
            checkParts(parts, line);
            values.put(parts[0], parts[1]);
        }
    }

    private void check(String line) {
        if (!line.startsWith("-")) {
            throw new IllegalArgumentException("Error: This argument '" + line + "' does not start with a '-' character");
        }
        if (!line.contains("=")) {
            throw new IllegalArgumentException("Error: This argument '" + line + "' does not contain an equal sign");
        }
    }

    private void checkParts(String[] parts, String line) {
        if (parts[0].length() == 1) {
            throw new IllegalArgumentException("Error: This argument '" + line + "' does not contain a key");
        }
        if (parts[1].length() == 0) {
            throw new IllegalArgumentException("Error: This argument '" + line + "' does not contain a value");
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}