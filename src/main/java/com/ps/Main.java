package com.ps;


import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    // main method 
    public static void main (String [] args) {
        // Begin create menu Instructions

        // initialize the command variable

        int mainMenuCommand;

        do {

            // display menu
            System.out.println("Please enter an option:");
            System.out.println("1) Add Deposit ");
            System.out.println("2) Make Payment (Debit)");
            System.out.println("3) Ledger");
            System.out.println("4) Exit");

            // import scanner, create instance of scanner, use scanner to store user's command as variable

            try {
                Scanner commandScanner = null;
                mainMenuCommand = commandScanner.nextInt();
            } catch (InputMismatchException ime) {
                mainMenuCommand = 4;
            }

            // switch statement to match the user command to the provided cases

        } while (mainMenuCommand != 4);

        switch (mainMenuCommand) {
            case 1:
                addDeposit();
                break;
            case 2:
                makePayment();
                break;
            case 3:
                displaySubMenu();
                break;
            case 4:
                System.out.println("Exiting");
                break;
            default:
                //handle incorrect commands
                System.out.println("Command not found, please try again.");
        }
        while (mainMenuCommand != 4);

    }

    public static void addDeposit() {
        System.out.println();
    }

    public static void makePayment(){
    }
    public static void displaySubMenu () {

        int subMenuCommand;

        // create a do while loop

        do {
            System.out.println(" Please select an option");
            System.out.println("1) Display all entries");
            System.out.println("2) Deposits");
            System.out.println("3) Payments");
            System.out.println("4) Reports");
            System.out.println("0) Going back to the main menu.");


            // switch statement to trigger according static method
            switch (subMenuCommand) {
                case 1:
                    displayAllEntries();
                    break;
                case 2:
                    deposits();
                    break;
                case 3:
                    payments();
                    break;
                case 4:
                    reports();
                    break;
                case 5:
                    System.out.println("Going back to the main menu.");
                default:
                    System.out.println("Command not found. Please try again.");
            }
        } while (subMenuCommand != 0);


    }
    public static void displayAllEntries() {
    }
    public static void deposits() {
    }





//class curly braces
}

        
        
        
    }

   

