package com.sabahtalateh.jenkov_tutorials.logging;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * BasicExample.
 */
public class BasicExample {

    /**
     * @param args atgs.
     * @throws IOException exception.
     */
    public static void main(String[] args) throws IOException {
        new Logging().method();
    }

    /**
     * Logging.
     */
    static class Logging {
        private static final Logger LOGGER = Logger.getLogger(Logging.class.getName());

        /**
         * @throws IOException exception.
         */
        public void method() throws IOException {
            ConsoleHandler consoleHandler = new ConsoleHandler();
            FileHandler fileHandler = new FileHandler("jenkov_tutorials/out/log.out");

            LOGGER.setLevel(Level.FINE);
            LOGGER.addHandler(consoleHandler);
            LOGGER.addHandler(fileHandler);

//            System.out.println(LOGGER.getHandlers());
            LOGGER.log(Level.FINE, "Jopa");

            System.out.println(LOGGER);
            System.out.println(LOGGER.getParent());
            System.out.println(LOGGER.getUseParentHandlers());
//            LOGGER.fine("Finer");
//            LOGGER.entering(getClass().getName(), "method");
//            LOGGER.log(Level.SEVERE, "Hello {0}", new RuntimeException("Error"));
//
//
//            try {
//                throw new Exception("Exc");
//            } catch (Exception e) {
//                LOGGER.log(Level.SEVERE, "Error while method", e);
//            }
//
//            LOGGER.exiting(getClass().getName(), "method");
        }
    }
}
