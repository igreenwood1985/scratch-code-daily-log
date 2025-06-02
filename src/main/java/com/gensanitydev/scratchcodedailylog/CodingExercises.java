package com.gensanitydev.scratchcodedailylog;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
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
        System.out.println("This exercise uses a file reader and a file writer");
        System.out.println("to produce/read/update a file");

        final String BEGIN_MARKER = "*** START OF";
        final String END_MARKER = "*** END OF";

            /*
             * This book-reader program opens a file that was downloaded from https://www.gutenberg.org/, reads
             * through the copyright information at the top until it finds the start of the book content, and
             * then displays the content to the user. It also counts the total lines of book content between the
             * start and the end markers.
             */

        /*
        Step 1: Prompt the user for a filename
         */
            // Create a scanner for user input
            Scanner userInput = new Scanner(System.in);
            // Prompt the user for a file path - path should look like "data/jekyll-and-hyde.txt"
            System.out.print("Enter path to the book file: ");
            String filePath = userInput.nextLine();

        /*
        Step 2: Step Two: Open the book file and handle errors
         */

            File bookFile = new File(filePath);


            try(Scanner fileInput = new Scanner(bookFile)){
                // Loop until end of file is reached

                int lineCount = 0;

                while(fileInput.hasNextLine()) {

                    // Read the next line into lineOfText
                    String lineOfText = fileInput.nextLine();

                    // Increment lineCount
                    lineCount++;

                    //Print the line
                    System.out.println(lineCount + ": " + lineOfText);

                }


            } catch (FileNotFoundException e){
                // Could not find the file at the specified path.
                System.out.println("File not found " + bookFile.getAbsolutePath());
            }




    }
}
