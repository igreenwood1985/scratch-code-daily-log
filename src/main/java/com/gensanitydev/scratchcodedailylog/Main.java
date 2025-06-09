package com.gensanitydev.scratchcodedailylog;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        //Class Variables
        PromptMenu messagePrompt = new PromptMenu();
        CodingExercises selectedExercise = new CodingExercises();

        // Java Datatype Variables
        String choiceCommand = "";
        String continueTesting = "Y";

        // Java Scanner Call
        Scanner in = new Scanner(System.in);


        //Shows Title Message
        messagePrompt.showWelcomeMessage();

        //Shows user their options and prompts them for selection input
        messagePrompt.showExercises();

        while (continueTesting.equalsIgnoreCase("Y")){
            //Get's the user's selection input
            choiceCommand = messagePrompt.getExerciseSelection();
            System.out.println(" ");


            // If statements to call user selection
            if(choiceCommand.equals("1")){

                // Exercise 1;
                selectedExercise.exerciseOne();
            }
            else if (choiceCommand.equals("2")){

                // Exercise 2;
                selectedExercise.exerciseTwo();
            }
            else if (choiceCommand.equals("3")){

                // Exercise 3;
                selectedExercise.exerciseThree();
            }
            else if (choiceCommand.equals("4")){

                // Exercise 4;
                selectedExercise.exerciseFour();
            } else {

                // Prompts user to select a different option
                messagePrompt.unavailableNotice();
            }

            //Asks User if they want to test another coding Exercise
            messagePrompt.selectAnotherOption();

            //Receives User's Yes or No answer
            choiceCommand = in.nextLine();

            // Sets continueTesting condition for while loop based on user's choice.
            if(choiceCommand.equalsIgnoreCase("Y")){
                continueTesting = "Y";
            } else {
                continueTesting = "N";
            }

        }
        System.out.println(" ");
        System.out.println("Study hard and keep trying! Code daily!");


    }


}
