package com.gensanitydev.scratchcodedailylog;

import java.util.Scanner;



public class PromptMenu {

    private static final Scanner in = new Scanner(System.in);

    public PromptMenu(){

    }

    public void showWelcomeMessage() {

        System.out.println("*****************************");
        System.out.println("**         Welcome         **");
        System.out.println("**          to my          **");
        System.out.println("**          Daily          **");
        System.out.println("**       Scratch Code      **");
        System.out.println("**       Practice Log      **");
        System.out.println("*****************************");
        System.out.println();

    }

    public void showExercises(){
        System.out.println("Please choose the exercise you want to test run: ");
        System.out.println("");
        System.out.println("1) Coding Exercise 1: Vote Eligibility");
        System.out.println("   - 5/26/2025");
        System.out.println("");
        System.out.println("2) Coding Exercise 2: Classroom Grades");
        System.out.println("   - 5/28/2025");
        System.out.println("");
        System.out.println("3) Coding Exercise 3: Reading/Redacting Files");
        System.out.println("   - 5/30/2025");
        System.out.println("");
        System.out.println("4) Coding Exercise 4: Writing/Appending Files");
        System.out.println("   - 6/09/2025");
        System.out.println("");
        System.out.println("5) Coding Exercise 5: Refined File Writer Program");
        System.out.println("   - 6/09/2025");
        System.out.println("");
        System.out.println("6) Grade Book Log Program");
        System.out.println("   - 6/10/2025");
        System.out.println("");

    }

    public String getExerciseSelection(){
        System.out.print("Please choose the Exercise you want to test: ");
        String exerciseSelection = in.nextLine();
        return exerciseSelection;
    }


    public void unavailableNotice(){
        System.out.println("This option is not yet available, please select another option");
    }

    public void selectAnotherOption(){
        System.out.println(" ");
        System.out.print("Would you like to select another exercise to test? Y/N: ");
    }



}
