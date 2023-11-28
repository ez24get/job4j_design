package ru.job4j.io.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "dealership")
public class Dealership {
    @XmlAttribute
    private String name;

    public Dealership() { }
    public Dealership(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dealership{"
                + "name='" + name + '\''
                + '}';
    }
}
