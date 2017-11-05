package com.sabahtalateh.jenkov_tutorials.json.jackson;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.IOException;

/**
 * ParserExample.
 */
public class ParserExample {
    /**
     * @param args args.
     */
    public static void main(String[] args) {
        String json = "{\"menu\": {\n"
                + "  \"id\": \"file\",\n"
                + "  \"value\": \"File\",\n"
                + "  \"cost\": 12.5,"
                + "  \"popup\": {\n"
                + "    \"menuitem\": [\n"
                + "      {\"value\": \"New\", \"onclick\": \"CreateNewDoc()\"},\n"
                + "      {\"value\": \"Open\", \"onclick\": \"OpenDoc()\"},\n"
                + "      {\"value\": \"Close\", \"onclick\": \"CloseDoc()\"}\n"
                + "    ]\n"
                + "  }\n"
                + "}}";


        try {
            JsonFactory factory = new JsonFactory();
            JsonParser parser = factory.createParser(json);

            while (!parser.isClosed()) {
                JsonToken token = parser.nextToken();
                if (JsonToken.FIELD_NAME.equals(token)) {
                    if ("cost".equals(parser.getCurrentName())) {
                        System.out.println(parser.getCurrentName());
                        parser.nextToken();
                        System.out.println(parser.getValueAsDouble());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
