package com.gensanitydev.scratchcodedailylog;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class CodingExercises {

    // This class contains all coding exercises that are hard-coded into the program,
    // to be called as methods in the class "Main"

    
    //Class Constructor
    public CodingExercises(){

    }

    public void exerciseOne(){

        System.out.println("You have chosen Exercise 1 started on May 26th 2025");
        System.out.println(" ");


        //Write your code within the exerciseOne() method below this line
        Scanner input = new Scanner(System.in);
        String keepVoting = "Y";


        while (keepVoting.equalsIgnoreCase("Y")){

            System.out.print("What is your name? ");
            String name = input.nextLine();
            System.out.print("Hello " + name + "!" + " How old are you? ");
            int age = Integer.parseInt(input.nextLine());

            if(age >= 18){
                System.out.println("You are eligible to vote!");
            } else {
                System.out.println("Sorry, you are too young to vote.");
            }

            System.out.print("Would someone else like to vote? " );
            keepVoting = input.nextLine();

        }


    }

    public void exerciseTwo(){

        //Write your code within the exerciseTwo() method below this line
        System.out.println("You have chosen Exercise 2, started on May 28th, 2025");
        System.out.println(" ");

        Scanner input = new Scanner(System.in);
        String selectAction = "Y";

        System.out.print("Please enter the number of students you are grading: ");
        String arrayInput = input.nextLine();

        int numberOfStudents = Integer.parseInt(arrayInput);

        int[] testScores = new int[numberOfStudents];

        for(int i = 0; i <= testScores.length - 1; i++) {

            int studentIDNum = i + 1;
            System.out.print("Enter score of student " + studentIDNum + ": ");
            String scoreEntry = input.nextLine();
            testScores[i] = Integer.parseInt(scoreEntry);
        }



        while (selectAction.equalsIgnoreCase("Y")){
            System.out.println("What information do you need?");
            System.out.println(" ");
            System.out.println("1 - Show class size");
            System.out.println(" ");
            System.out.println("2 - Show all Scores");
            System.out.println(" ");
            System.out.println("3 - Show average score");
            System.out.println(" ");
            System.out.println("4 - Show highest score");
            System.out.println(" ");
            System.out.println("5 - Show lowest score");
            System.out.println(" ");
            if(numberOfStudents >= 10){
                System.out.println("6 - Show modal score");
                System.out.println(" ");

            }

            System.out.print("Please select corresponding number: ");
            System.out.println(" ");

            String choiceAction = input.nextLine();

            int choiceNumber = Integer.parseInt(choiceAction);

            if(choiceNumber == 1){
                System.out.println("This is the number of students in your class");
                System.out.println(" ");
                System.out.println(numberOfStudents);

            }
            else if (choiceNumber == 2){
                System.out.println("Here are all of you test scores");
                System.out.println(" ");
                System.out.println(Arrays.toString(testScores));

            }
            else if (choiceNumber == 3){

                int sum = Arrays.stream(testScores).sum();
                int averageScore = sum/numberOfStudents;

                System.out.println("The average testScore for this class is: " + averageScore);
                System.out.println(" ");

            } else if (choiceNumber == 4) {

                System.out.println("The highest score in this class is: "
                        + Arrays.stream(testScores).max().getAsInt());
                System.out.println(" ");

            } else if (choiceNumber == 5) {

                System.out.println("The lowest score in this class is: "
                        + Arrays.stream(testScores).min().getAsInt());
                System.out.println(" ");

            } else if (choiceNumber == 6){

                HashMap<Integer, Integer> countMap = new HashMap<>();
                int mode = testScores[0];
                int maxCount = 0;

                for (int num : testScores) {
                    int count = countMap.getOrDefault(num, 0) + 1;
                    countMap.put(num, count);

                    if (count > maxCount) {
                        maxCount = count;
                        mode = num;
                    }
                }

                System.out.println("Test score mode for this class is: " + mode);
            }

            System.out.print("Would you like more information about this class's test scores? " );
            selectAction = input.nextLine();
        }



    }

    public void exerciseThree(){

        System.out.println(" ");
        System.out.println("This exercise uses a file reader");
        System.out.println("to skip redacted lines while showing");
        System.out.println("lines that are public information");

        final String PUBLIC_MARKER = "*** Public";
        final String REDACTED_MARKER = "*** Top Secret";
        final String END_MARKER = "*** End";



        /*
        Prompt the user for a filename
         */
            // Create a scanner for user input
            Scanner userInput = new Scanner(System.in);

            // Prompt the user for a file path - path should look like "data/jekyll-and-hyde.txt"
            System.out.print("Enter path to the reading file: ");
            String filePath = userInput.nextLine();

        /*
        Open the file to be read and handle errors
         */

            File foiaFile = new File(filePath);

            boolean viewableText = false;

            try(Scanner fileInput = new Scanner(foiaFile)){
                // Loop until end of file is reached

                int lineCount = 0;

                while(fileInput.hasNextLine()){

                    // Read the next line into lineOfText
                    String lineOfText = fileInput.nextLine();

                    // Skip header information before file content
                    if( lineOfText.startsWith(PUBLIC_MARKER)) {
                        viewableText = true;
                        continue; // no need to process this line... go to next line
                    }

                    if(lineOfText.startsWith(REDACTED_MARKER)){
                        viewableText = false; //Redact lineOfText

                        //Increment lineCount
                        lineCount++;
                        System.out.println( lineCount + ": Redacted");
                    }

                    if(lineOfText.startsWith(END_MARKER)) {
                        break; // Ends program at the end of the document
                    }

                    // Only prints lines of text when inBookText = true
                    if(viewableText) {
                        // Increment lineCount
                        lineCount++;


                        //Print the line
                        System.out.println( lineCount + ": " + lineOfText);

                    }
                }


            } catch (FileNotFoundException e){
                // Could not find the file at the specified path.
                System.out.println("File not found " + foiaFile.getAbsolutePath());
            }




    }


    public void exerciseFour(){
        System.out.println("This is an exercise in which file I/O writer is used ");
        System.out.println("to create a file that can then be appended with new ");
        System.out.println("entries by the user. ");

        String continueInput = " ";
        boolean addLine = true;
        Scanner userInput = new Scanner(System.in);
        String filePath = "data/journal.txt";

        while(addLine) {
            //Write code below this line


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

            if (continueInput.equalsIgnoreCase("Y")){
                addLine = true;
            } else {
                addLine = false;
            }

        }

    }

    public void exerciseFive() {
        System.out.println("This is a refined file writer program. It allows the user");
        System.out.println("to move on to the next file by simply pressing ENTER. When");
        System.out.println("the user types /exit into the command line,");
        System.out.println("the program exits.");
        System.out.println(" ");
        System.out.println(" ");


        //Declarations and Variables
        String continueInput = " ";
        boolean addLine = true;
        String filePath = "data/coding-diary.txt";

        Scanner userInput = new Scanner(System.in);


        //User Instructions
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
                addLine = true;
            }

            try(PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))){
                writer.println(lineToWrite);
            } catch (IOException e) {
                System.out.println("an error occurred: " + e.getMessage());
            }
        }
    }

    public void exerciseSix(){
        System.out.println("In this exercise, you will write a program using what you have ");
        System.out.println("previously learned to write a grade book program that keeps a log");
        System.out.println("of student grades and records time and date of entries.");

        //Write code below this line
    }
}
