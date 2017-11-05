package com.sabahtalateh.jenkov_tutorials.json.jackson;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import java.io.IOException;
import java.io.StringWriter;

/**
 * GeneratorExample.
 */
public class GeneratorExample {
    /**
     * @param args args.
     */
    public static void main(String[] args) {
        JsonFactory factory = new JsonFactory();
        try {
            StringWriter sw = new StringWriter();
            JsonGenerator generator = factory.createGenerator(sw);
            generator.writeStartObject();
            generator.writeStringField("name", "Ivan");
            generator.writeNumberField("cost", 99);
            generator.writeEndObject();
            generator.close();

            System.out.println(sw.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
