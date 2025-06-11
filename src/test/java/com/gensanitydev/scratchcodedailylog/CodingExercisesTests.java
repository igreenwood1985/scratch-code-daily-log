package com.gensanitydev.scratchcodedailylog;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CodingExercisesTests {


    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outputStream));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    public void testExerciseOne_withEligibleAge() {
        String simulatedInput = "John\n20\nN\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        CodingExercises exercises = new CodingExercises();
        exercises.exerciseOne();

        String output = outputStream.toString();
        assertTrue(output.contains("You are eligible to vote!"));
    }

    @Test
    public void testExerciseOne_withUnderage() {
        String simulatedInput = "Alice\n15\nN\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        CodingExercises exercises = new CodingExercises();
        exercises.exerciseOne();

        String output = outputStream.toString();
        assertTrue(output.contains("Sorry, you are too young to vote."));
    }

    @Test
    public void testExerciseTwo_averageCalculation() {
        String simulatedInput =
                "5\n" +              // number of students
                        "80\n90\n70\n60\n100\n" + // scores for 5 students
                        "3\n" +              // choose option to view average
                        "N\n";               // choose not to continue

        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        outputStream.reset();

        CodingExercises exercises = new CodingExercises();
        exercises.exerciseTwo();

        String output = outputStream.toString();
        assertTrue(output.contains("The average testScore for this class is: 80"));
    }

    @Test
    public void testExerciseThree_redactsAndDisplaysProperly() throws IOException {
        // Setup temporary file
        File tempFile = File.createTempFile("testfile", ".txt");
        tempFile.deleteOnExit();

        PrintWriter writer = new PrintWriter(tempFile);
        writer.println("*** Public ***");
        writer.println("Visible Line 1");
        writer.println("Visible Line 2");
        writer.println("*** Top Secret ***");
        writer.println("This should be hidden");
        writer.println("*** Public ***");
        writer.println("Visible Line 3");
        writer.println("*** End");
        writer.println("This should not be shown");
        writer.close();

        // Simulate file path input
        String simulatedInput = tempFile.getPath() + "\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        outputStream.reset();

        CodingExercises exercises = new CodingExercises();
        exercises.exerciseThree();

        String output = outputStream.toString();

        // Assertions
        assertTrue(output.contains("1: Visible Line 1"));
        assertTrue(output.contains("2: Visible Line 2"));
        assertTrue(output.contains("3: Redacted"));
        assertTrue(output.contains("4: Visible Line 3"));
        assertFalse(output.contains("This should not be shown")); // line after *** End
    }

    @Test
    public void testExerciseFour_appendsLinesToFile() throws IOException {
        // Prepare test file
        File tempFile = File.createTempFile("testjournal", ".txt");
        tempFile.deleteOnExit();
        String filePath = tempFile.getAbsolutePath();

        // Simulate user input: 2 lines + "N" to stop
        String simulatedInput = String.join("\n",
                "First line of journal",
                "Y",
                "Second line of journal",
                "N"
        ) + "\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        outputStream.reset();

        // Inject file path into method (via minor refactor or reflection)
        CodingExercises exercises = new CodingExercises() {
            @Override
            public void exerciseFour() {
                System.out.println("This is an exercise in which file I/O writer is used ");
                System.out.println("to create a file that can then be appended with new ");
                System.out.println("entries by the user. ");

                String continueInput = " ";
                boolean addLine = true;
                Scanner userInput = new Scanner(System.in);

                while(addLine) {
                    System.out.print("Type a line to append to journal.txt: ");
                    String lineToWrite = userInput.nextLine();

                    try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
                        writer.println(lineToWrite);
                        System.out.println("\"" + lineToWrite + "\"" + " successfully added to file.");
                    } catch (IOException e) {
                        System.out.println("an error occurred: " + e.getMessage());
                    }

                    System.out.print("Would you like to add another line to the document? Y/N: ");
                    continueInput = userInput.nextLine();

                    addLine = continueInput.equalsIgnoreCase("Y");
                }
            }
        };

        exercises.exerciseFour();

        // Read the file to confirm contents
        String content = Files.readString(tempFile.toPath());
        assertTrue(content.contains("First line of journal"));
        assertTrue(content.contains("Second line of journal"));
    }

    @Test
    public void testExerciseFive_appendsUntilExit() throws IOException {
        // Prepare a temporary file to simulate diary
        File tempFile = File.createTempFile("testdiary", ".txt");
        tempFile.deleteOnExit();
        String filePath = tempFile.getAbsolutePath();

        // Simulated user input: 3 lines, then "/exit"
        String simulatedInput = String.join("\n",
                "*** Public ***",
                "Today I practiced coding and wrote test cases.",
                "/exit"
        ) + "\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        outputStream.reset();

        // Override file path inside method using anonymous class
        CodingExercises exercises = new CodingExercises() {
            @Override
            public void exerciseFive() {
                System.out.println("This is a refined file writer program. It allows the user");
                System.out.println("to move on to the next file by simply pressing ENTER. When");
                System.out.println("the user types /exit into the command line,");
                System.out.println("the program exits.");
                System.out.println(" ");
                System.out.println(" ");

                String continueInput = " ";
                boolean addLine = true;

                Scanner userInput = new Scanner(System.in);

                System.out.println("Please enter the text you wish to add to the diary.");
                System.out.println(" ");
                System.out.println("To finish your current line and move to the next line,.");
                System.out.println("press ENTER.");
                System.out.println(" ");
                System.out.println("To finish your entry, type /exit.");
                System.out.println(" ");
                System.out.println("For publicly viewable information, start the first line");
                System.out.println("with *** Public ***");
                System.out.println(" ");
                System.out.println("To write a redacted line in the diary, start the first line");
                System.out.println("with *** Top Secret *** ");
                System.out.println(" ");
                System.out.println("Enter the your text:");

                while(addLine){
                    String lineToWrite = userInput.nextLine();

                    if(lineToWrite.equalsIgnoreCase("/EXIT")){
                        addLine = false;
                    } else {
                        try(PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))){
                            writer.println(lineToWrite);
                        } catch (IOException e) {
                            System.out.println("an error occurred: " + e.getMessage());
                        }
                    }
                }
            }
        };

        exercises.exerciseFive();

        // Validate written lines
        String content = Files.readString(tempFile.toPath());
        assertTrue(content.contains("*** Public ***"));
        assertTrue(content.contains("Today I practiced coding and wrote test cases."));
        assertFalse(content.contains("/exit")); // should not be written
    }
}



