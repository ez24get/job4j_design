package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {
        final Person person = new Person(false, 30, new Contact("11-111"),
                new String[] {"Worker", "Married"});
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(person));
        final String personJson =
                "{"
                        + "\"sex\":false,"
                        + "\"age\":35,"
                        + "\"contact\":"
                        + "{"
                        + "\"phone\":\"+7(924)111-111-11-11\""
                        + "},"
                        + "\"statuses\":"
                        + "[\"Student\",\"Free\"]"
                        + "}";
        final Person personMod = gson.fromJson(personJson, Person.class);
        System.out.println(personMod);
        final Car car = new Car(false, "Ford", 2021, new DealershipInfo("Avilon"),
                new String[] {"Left back passenger door", "Stoplight right"});
        final Gson gson2 = new GsonBuilder().create();
        System.out.println(gson2.toJson(car));
        final String carJSon =
                "{"
                        + "\"isNew\":false,"
                        + "\"brand\":BMW,"
                        + "\"year\":2020,"
                        + "\"info\":"
                        + "{"
                        + "\"name\":\"Borishof\""
                        + "},"
                        + "\"defect\":"
                        + "[\"Right front passenger door\",\"Headlights\"]"
                        + "}";
        final Car jsonToCar = gson2.fromJson(carJSon, Car.class);
        System.out.println(jsonToCar);
        final String carXML = """
                <?xml version="1.1" encoding="UTF-8" ?>
                <car isNew="false" brand="Ford" year="2020">
                    <info name="Borishof"/>
                    <defects>
                        <defect>Right front passenger door</defect>
                        <defect>Headlights</defect>
                    </defects>
                </car>""";
        System.out.println(carXML);
        JSONObject jsonObjectCar = new JSONObject();
        jsonObjectCar.put("isNew", car.isNew());
        jsonObjectCar.put("brand", car.getBrand());
        jsonObjectCar.put("year", car.getYear());
        jsonObjectCar.put("info", car.getInfo());
        jsonObjectCar.put("defect", car.getDefect());
        System.out.println(jsonObjectCar);
        System.out.println(new JSONObject(car));
    }
}