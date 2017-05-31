package io.github.thiagogcm.javastarter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    @Test
    @DisplayName("Test one")
    public void testOne() {
        assertEquals(2, 1 + 1);
    }
}
