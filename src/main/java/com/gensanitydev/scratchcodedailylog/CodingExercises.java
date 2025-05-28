package com.gensanitydev.scratchcodedailylog;

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
        System.out.println("You have chosen Exercise 2, which is not yet in progress");
        System.out.println(" ");

    }

}
