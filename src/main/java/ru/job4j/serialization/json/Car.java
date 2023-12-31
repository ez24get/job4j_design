package ru.job4j.serialization.json;

import java.util.Arrays;

public class Car {
    private final boolean isNew;
    private final String brand;
    private final int year;
    private final DealershipInfo info;
    private final String[] defect;

    public Car(boolean isNew, String brand, int year, DealershipInfo info, String[] defect) {
        this.isNew = isNew;
        this.brand = brand;
        this.year = year;
        this.info = info;
        this.defect = defect;
    }

    public boolean isNew() {
        return isNew;
    }

    public String getBrand() {
        return brand;
    }

    public int getYear() {
        return year;
    }

    public DealershipInfo getInfo() {
        return info;
    }

    public String[] getDefect() {
        return defect;
    }

    @Override
    public String toString() {
        return "Car{"
                + "new=" + isNew
                + ", brand=" + brand
                + ", year=" + year
                + ", info=" + info
                + ", defects=" + Arrays.toString(defect)
                + '}';
    }
}
