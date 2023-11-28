package ru.job4j.io.serialization.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {
    private boolean isNew;
    @XmlAttribute
    private String brand;
    @XmlAttribute
    private int year;
    private Dealership info;
    @XmlAttribute
    private String[] defect;

    public Car() {

    }
    public Car(boolean isNew, String brand, int year, Dealership info, String[] defect) {
        this.isNew = isNew;
        this.brand = brand;
        this.year = year;
        this.info = info;
        this.defect = defect;
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
