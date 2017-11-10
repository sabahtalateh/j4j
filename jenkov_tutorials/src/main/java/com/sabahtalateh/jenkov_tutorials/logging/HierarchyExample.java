package com.sabahtalateh.jenkov_tutorials.logging;

import java.util.logging.*;

/**
 * HierarchyExample.
 */
public class HierarchyExample {
    /**
     * @param args args.
     */
    public static void main(String[] args) {
        Logger root = Logger.getLogger("");
        Logger l1 = Logger.getLogger("1");
        Logger l12 = Logger.getLogger("1.2");

        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                return "Jopa";
            }
        });

        l1.addHandler(handler);
        l12.addHandler(handler);

//        l1.setFilter(record -> false);
        l1.setLevel(Level.WARNING);
        l12.setLevel(Level.INFO);

        root.info("msg:");
        l1.info("msg: 1");
        l12.info("msg: 1.2");
    }
}
