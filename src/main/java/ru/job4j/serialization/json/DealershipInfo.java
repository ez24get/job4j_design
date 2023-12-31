package ru.job4j.serialization.json;

public class DealershipInfo {
    private final String name;

    public DealershipInfo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Dealership{"
                + "name='" + name + '\''
                + '}';
    }
}
