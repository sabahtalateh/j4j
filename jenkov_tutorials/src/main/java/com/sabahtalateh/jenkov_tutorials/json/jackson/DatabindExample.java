package com.sabahtalateh.jenkov_tutorials.json.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;

/**
 * DatabindExample.
 */
public class DatabindExample {
    /**
     * @param args args.
     */
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        String carJSON = "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";

        String carJSONArray = "[{ \"brand\" : \"Mercedes\", \"doors\" : 5 }]";

        try {
            Car car0 = objectMapper.readValue(carJSON, Car.class);
            System.out.println(car0.getBrand());
            System.out.println(car0.getDoors());

            StringReader stringReader = new StringReader(carJSON);

            Car car1 = objectMapper.readValue(stringReader, Car.class);
            System.out.println(car1.getBrand());
            System.out.println(car1.getDoors());

            Car[] cars = objectMapper.readValue(carJSONArray, Car[].class);
            System.out.println(Arrays.toString(cars));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Car car2 = new Car();
        car2.setBrand("Volvo");
        car2.setDoors(93);
        try {
//            objectMapper.writeValue(System.out, car2);
            System.out.println(objectMapper.writeValueAsString(car2));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            JsonNode carNode = objectMapper.readValue(carJSON, JsonNode.class);
            System.out.println(carNode);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 5,"
                + "  \"owners\" : [\"John\", \"Jack\", \"Jill\"],"
                + "  \"nestedObject\" : { \"field\" : \"value\" } }";

        try {
            JsonNode node = objectMapper.readValue(carJson, JsonNode.class);

            JsonNode brandNode = node.get("brand");
            System.out.println(brandNode.asText());

            JsonNode doorsNode = node.get("doors");
            System.out.println(doorsNode.asInt());

            JsonNode ownersNode = node.get("owners");
            System.out.println(ownersNode.get(0).asText());
            System.out.println(ownersNode.get(1).asText());

            JsonNode child = node.get("nestedObject");
            System.out.println(child.get("field").asText());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
