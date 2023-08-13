package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = read.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2 && parts[0].length() != 0 && parts[1].length() != 0) {
                    values.put(parts[0], parts[1]);
                } else if (parts.length > 2) {
                    int length = parts.length;
                    for (int i = 1; i < length; i++) {
                        parts[1] = parts[1] + "=" + parts[i];
                    }
                    values.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        String result;
        try {
            result = values.get(key);
        } catch (UnsupportedOperationException uoe) {
            throw new UnsupportedOperationException("Don't impl this method yet!");
        }
        return result;
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }
}