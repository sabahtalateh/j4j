package com.sabahtalateh.jenkov_tutorials.json.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sabahtalateh.jenkov_tutorials.json.Car;

/**
 * Example.
 */
public class Example {
    /**
     * @param args args.
     */
    public static void main(String[] args) {
        String json = "{\"brand\":\"Jeep\", \"doors\": 3}";

        Gson gson = new Gson();
        Car car = gson.fromJson(json, Car.class);
        System.out.println(car);

        car = new Car();
        car.setBrand("Machina");
        car.setDoors(27);

        Gson gsonPretty = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .serializeNulls()
                .create();

        json = gsonPretty.toJson(car);
        System.out.println(json);

    }
}