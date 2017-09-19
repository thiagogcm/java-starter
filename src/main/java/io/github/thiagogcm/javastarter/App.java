package io.github.thiagogcm.javastarter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("Starting App");

        System.out.println("Hello World, Starter!");
    }

    public int result(int a, int b) {
        return a + b;
    }
}
