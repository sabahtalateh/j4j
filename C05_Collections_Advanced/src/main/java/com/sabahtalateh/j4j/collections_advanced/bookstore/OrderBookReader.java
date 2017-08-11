package com.sabahtalateh.j4j.collections_advanced.bookstore;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * OrderBookReader.
 */
public class OrderBookReader {

    /**
     * @param xmlFilePath path to xml file.
     * @return store.
     * @throws ParserConfigurationException exception.
     * @throws SAXException                 exception.
     * @throws IOException                  exception.
     */
    public Store storeFromXml(String xmlFilePath) throws ParserConfigurationException, SAXException, IOException {
        File inputFile = new File(xmlFilePath);
        SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();

        Map<Integer, OrderBookEntry> orders = new HashMap<>(1 << 10);
        saxParser.parse(inputFile, new OrderBookHandler(orders));

        Store store = new Store();
        for (OrderBookEntry entry : orders.values()) {
            store.addOrder(entry.productName, entry.type, entry.volume, entry.price);
        }

        return store;
    }

    /**
     * OrderBookHandler.
     */
    private static class OrderBookHandler extends DefaultHandler {

        Map<Integer, OrderBookEntry> orders;

        /**
         * @param orders orders.
         */
        OrderBookHandler(Map<Integer, OrderBookEntry> orders) {
            this.orders = orders;
        }

        /**
         * @param uri        uri.
         * @param localName  local name.
         * @param qName      query name.
         * @param attributes attributes.
         * @throws SAXException exception.
         */
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("AddOrder")) {
                this.orders.put(
                        Integer.valueOf(attributes.getValue("orderId")),
                        new OrderBookEntry(
                                attributes.getValue("book"),
                                attributes.getValue("operation").equals("SELL") ? OrderType.BID : OrderType.ASK,
                                Double.valueOf(attributes.getValue("price")),
                                Integer.valueOf(attributes.getValue("volume"))
                        )
                );
            } else if (qName.equals("DeleteOrder")) {
                this.orders.remove(Integer.valueOf(attributes.getValue("orderId")));
            }
        }
    }
}
