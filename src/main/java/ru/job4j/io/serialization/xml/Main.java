package ru.job4j.io.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        Person person = new Person(false, 30, new Contact("11-111"), "Worker", "Married");
        JAXBContext context = JAXBContext.newInstance(Person.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml;
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(person, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Person result = (Person) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
        Car car = new Car(false, "Ford", 2020, new Dealership("Avilon"),
                new String[] {"Left back passenger door", "Stoplight right"});
        JAXBContext context2 = JAXBContext.newInstance(Car.class);
        Marshaller marshaller2 = context2.createMarshaller();
        marshaller2.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml2;
        try (StringWriter writer2 = new StringWriter()) {
            marshaller2.marshal(car, writer2);
            xml2 = writer2.getBuffer().toString();
            System.out.println(xml2);
        }
        /* Для десериализации нам нужно создать десериализатор */
        Unmarshaller unmarshaller2 = context2.createUnmarshaller();
        try (StringReader reader2 = new StringReader(xml2)) {
            /* десериализуем */
            Car resultCar = (Car) unmarshaller2.unmarshal(reader2);
            System.out.println(resultCar);
        }
    }
}