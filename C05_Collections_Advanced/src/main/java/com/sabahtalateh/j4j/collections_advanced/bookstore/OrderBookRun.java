package com.sabahtalateh.j4j.collections_advanced.bookstore;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * OrderBookRun.
 */
public class OrderBookRun {
    /**
     * @param args args.
     * @throws IOException                  exception.
     * @throws SAXException                 exception.
     * @throws ParserConfigurationException exception.
     */
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        OrderBookReader orderBookReader = new OrderBookReader();
        Store store = orderBookReader.storeFromXml(args[0]);
        store.runBid();
        store.printReport(System.out);
    }
}
