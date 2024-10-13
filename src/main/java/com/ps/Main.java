package com.ps;


import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {


    // Begin create menu Instructions

    // initialize the command variable

    int mainMenuCommand;

    do {

        // display menu
        System.out.println("Please enter an option:");
        System.out.println("1) Add Deposit ");
        System.out.println("2) Make Payment (Debit)");
        System.out.println("3) Ledger");
        System.out.println("4) Exit";

        // import scanner, create instance of scanner, use scanner to store user's command as variable

        try {
            Scanner commandScanner;
            mainMenuCommand = commandScanner.nextInt();
        } catch (InputMismatchException ime) {
            mainMenuCommand = 0;
        }

        // switch statement to match the user command to the provided cases

        switch (mainMenuCommand) {
            case 1:
                addDeposit();
                break;
            case 2:
                makeDeposit();
                break;
            case 3:
                ledger();
                break;
            case 4:
                System.out.println("Exiting");
                break;
            default:
                //handle incorrect commands
                System.out.println("Command not found, please try again.");
        }

    } while (mainMenuCommand != 0);

    }
    public static void addDeposit() {
    }

    public static void makePayment(){
}
    public static void ledger (){

    }

