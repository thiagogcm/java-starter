package io.github.thiagogcm.javastarter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("Starting App");

        System.out.println("Starter!");
    }
}
