package com.gensanitydev.scratchcodedailylog;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class MainTests {

    @Test
    void main_doesNotThrowException() {
        assertDoesNotThrow(() -> Main.main(new String[]{}));
    }

}
