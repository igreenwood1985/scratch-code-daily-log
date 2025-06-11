package com.gensanitydev.scratchcodedailylog;

import org.junit.jupiter.api.*;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class PromptMenuTests {

    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    void testShowWelcomeMessage_printsExpectedOutput() {
        PromptMenu menu = new PromptMenu();
        menu.showWelcomeMessage();

        String printed = output.toString();
        assertTrue(printed.contains("Welcome"));
        assertTrue(printed.contains("Scratch Code"));
        assertTrue(printed.contains("Practice Log"));
    }

    @Test
    void testUnavailableNotice_printsExpectedMessage() {
        PromptMenu menu = new PromptMenu();
        menu.unavailableNotice();

        String printed = output.toString().trim();
        assertEquals("This option is not yet available, please select another option", printed);
    }

    @Test
    void testSelectAnotherOption_promptAppears() {
        PromptMenu menu = new PromptMenu();
        menu.selectAnotherOption();

        String printed = output.toString();
        assertTrue(printed.contains("Would you like to select another exercise to test?"));
    }

    @Test
    void testGetExerciseSelection_readsExpectedInput() {
        String input = "3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        PromptMenu menu = new PromptMenu();
        String selection = menu.getExerciseSelection();

        assertEquals("3", selection);
    }

}
