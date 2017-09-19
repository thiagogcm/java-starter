package io.github.thiagogcm.javastarter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    @Test
    @DisplayName("Test result")
    public void testOne() {
        App app = new App();
        assertEquals(2, app.result(1, 1));
    }
}
