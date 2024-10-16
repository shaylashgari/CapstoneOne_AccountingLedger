package com.ps;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Scanner commandScanner = new Scanner(System.in);
    static Scanner inputScanner = new Scanner(System.in);
    // create an array list that is going to keep track of all transactions
    // understand the context, keeping track of transactions, array list of transactions
    // static array list
    static ArrayList<Transaction> allTransaction = new ArrayList<>();


    // main method 
    public static void main (String [] args) {
        // Begin create menu Instructions
        getTransactions();
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
                mainMenuCommand = commandScanner.nextInt();
            } catch (InputMismatchException ime) {
                mainMenuCommand = 4;
            }

            // switch statement to match the user command to the provided cases

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

        } while (mainMenuCommand != 4);

    // main method end
    }

    public static void getTransactions () {

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("transactions.csv"));

            String firstLine = bufferedReader.readLine();
            String input;

            while ((input = bufferedReader.readLine()) != null) {
                String[] transactionArr = input.split("\\|"); //

                String date = transactionArr[0];
                String time = transactionArr[1];
                String description = (transactionArr[2]);
                String vendor = transactionArr[3];
                float amount = Float.parseFloat(transactionArr[4]);
                Transaction transaction = new Transaction(date, time, description, vendor, amount);
                allTransaction.add(transaction);
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static void addDeposit() {
        System.out.println("add deposit");
        System.out.println("Command for adding deposit");
        System.out.println("Please enter the details of the deposit...");


        System.out.print("Description: ");
        String description = inputScanner.nextLine();


        System.out.print("Vendor: ");
        String vendor = inputScanner.nextLine();

        System.out.print("How much do you want to deposit? ");
        float amount = inputScanner.nextFloat();
        inputScanner.nextLine();

        // create variables to store the current date time, local date time.now, get it self instead user
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss");

        String formattedDate = currentDateTime.format(dateFormatter);
        String formattedTime = currentDateTime.format(timeFormatter);

        Transaction transaction = new Transaction(formattedDate, formattedTime, description, vendor, amount);
        allTransaction.add(transaction);

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("transactions.csv", true));
            bufferedWriter.write(String.format("\n%s|%s|%s|%s|%f",
                    formattedDate,
                    formattedTime,
                    transaction.getDescription(),
                    transaction.getVendor(),
                    transaction.getAmount()

            ));

            bufferedWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Deposit was successful!");
    }


    public static void makePayment(){

    // same as deposit

        System.out.println("Make payment");

//        System.out.println("Command for adding deposit");

        System.out.println("Please enter the details of the payment...");


        System.out.println("Description: ");
        String description = inputScanner.nextLine();


        System.out.println("Vendor: ");
        String vendor = inputScanner.nextLine();

        System.out.println("How much do you want to make payment? ");
        float amountPrice = inputScanner.nextFloat();
        inputScanner.nextLine();
        float amount = -amountPrice;

        // create variables to store the current date time, local date time.now, get itself instead user
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss");

        String formattedDate = currentDateTime.format(dateFormatter);
        String formattedTime = currentDateTime.format(timeFormatter);

        Transaction transaction = new Transaction(formattedDate, formattedTime, description, vendor, amount);
        allTransaction.add(transaction);

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("transactions.csv", true));
            bufferedWriter.write(String.format("\n%s|%s|%s|%s|%f",
                    formattedDate,
                    formattedTime,
                    transaction.getDescription(),
                    transaction.getVendor(),
                    transaction.getAmount()

            ));

            bufferedWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Payment was successful!");
    }
    // this pools up the ledger menu, key part of project
    public static void displaySubMenu () {

        int subMenuCommand;
        subMenuCommand= 3;

        // create a do while loop

        do {
            System.out.println(" Please select an option");
            System.out.println("1) Display all entries");
            System.out.println("2) Deposits");
            System.out.println("3) Payments");
            System.out.println("4) Reports");
            System.out.println("0) Going back to the main menu.");

            try {
                subMenuCommand = commandScanner.nextInt();
            } catch (InputMismatchException ime) {
                subMenuCommand = 0;
            }


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
                case 0:
                    System.out.println("Going back to the main menu.");
                    break;
                default:
                    System.out.println("Command not found. Please try again.");
            }
        } while (subMenuCommand != 0);


    }
    public static void displayAllEntries() {
      //  getTransactions();

        // for loop, iterating through array
        // array list of transactions

        System.out.println("Placeholder: Display All");
        for (int i = 0; i < allTransaction.size(); i++) {
            System.out.println(allTransaction.get(i));
        }
    }

    public static void deposits() {
        // if statement inside loop
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss");

        String formattedDate = currentDateTime.format(dateFormatter);
        String formattedTime = currentDateTime.format(timeFormatter);

        inputScanner.nextLine();

        System.out.println("Command for making a deposit");
        System.out.println("Please enter the details of the deposit...");

        System.out.print("Description: ");
        String description = inputScanner.nextLine();

        System.out.print("Vendor: ");
        String vendor = inputScanner.nextLine();

        System.out.print("Amount: ");
        int amount = inputScanner.nextInt();

        inputScanner.nextLine();


        Transaction transaction = new Transaction(formattedDate, formattedTime, description, vendor, amount);
        allTransaction.add(transaction);

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("transactions.csv", true));
            bufferedWriter.write(String.format("\n%s|%s|%d|%s|%b|%s|%s",
                    formattedDate,
                    formattedTime,
                    transaction.getDescription(),
                    transaction.getVendor(),
                    transaction.getAmount()
            ));

            bufferedWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void payments(){
        // same as deposit, negative takes money out

        // if statement inside loop
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss");

        String formattedDate = currentDateTime.format(dateFormatter);
        String formattedTime = currentDateTime.format(timeFormatter);

        System.out.println("Command for making a deposit");
        System.out.println("Please enter the details of the deposit...");

        System.out.print("Description: ");
        String description = inputScanner.nextLine();

        System.out.print("Vendor: ");
        String vendor = inputScanner.nextLine();

        System.out.print("Amount: ");
        int amount = inputScanner.nextInt();
        amount = amount * -1;
        inputScanner.nextLine();


        Transaction transaction = new Transaction(formattedDate, formattedTime, description, vendor, amount);
        allTransaction.add(transaction);

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("transactions.csv", true));
            bufferedWriter.write(String.format("\n%s|%s|%d|%s|%b|%s|%s",
                    formattedDate,
                    formattedTime,
                    transaction.getDescription(),
                    transaction.getVendor(),
                    transaction.getAmount()
                    ));

            bufferedWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void reports(){
        int reportCommand;

        do {


            System.out.println(" Please select a report type");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous year");
            System.out.println("5) Search by Vendor");
            System.out.println("6) custom search");
            System.out.println("0) Go Back To Reports Page");


            try {
                reportCommand = commandScanner.nextInt();
            } catch (InputMismatchException ime) {
                reportCommand = 0;
            }

            switch (reportCommand) {
                case 1:
                    monthToDate();
                    break;
                case 2:
                    previousMonth();
                    break;
                case 3:
                    yearToDate();
                    break;
                case 4: previousYear();
                    break;
                case 5:
                    searchByVendor();
                    break;
                case 6:
                    customSearch();
                case 0:
                    System.out.println("Going back to the main menu.");
                    break;
                default:
                    System.out.println("Command not found. Please try again.");
            }
        } while (reportCommand != 0);

    }

    // method for report goes here, from the beginning of month to now
   public static void monthToDate() {
       LocalDate today = LocalDate.now();
       int currentMonth = today.getMonthValue();
       int currentYear = today.getYear();

       for (Transaction transaction : allTransaction) {
           DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
           String transactionDate = transaction.getDate();

           LocalDate localDate = LocalDate.parse(transactionDate, dateFormatter);

           int transactionMonth = localDate.getMonthValue();
           int transactionYear = localDate.getYear();

           if (currentMonth == transactionMonth && currentYear == transactionYear) {
               System.out.println(transaction);
           }
       }
   }
   public static void previousMonth() {
       LocalDate today = LocalDate.now();
       int currentMonth = today.getMonthValue();
       int currentYear = today.getYear();

       // add logic here for --> is this correct?
       currentMonth = currentMonth - 1;

       for (Transaction transaction : allTransaction) {
           DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
           String transactionDate = transaction.getDate();

           LocalDate localDate = LocalDate.parse(transactionDate, dateFormatter);

           int transactionMonth = localDate.getMonthValue();
           int transactionYear = localDate.getYear();

           if (currentMonth == transactionMonth && currentYear == transactionYear) {
               System.out.println(transaction);
           }
       }
   }
   public static void yearToDate(){
        // previous yr: same thing, but insert currentyr= currentyr-1
       LocalDate today = LocalDate.now();
       int currentYear = today.getYear();

       // --> is this correct?
       currentYear= currentYear -1;

       for (Transaction transaction : allTransaction) {
           DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
           String transactionDate = transaction.getDate();

           LocalDate localDate = LocalDate.parse(transactionDate, dateFormatter);

           int transactionYear = localDate.getYear();

           if (currentYear == transactionYear) {
               System.out.println(transaction);
           }
       }

   }

   // previous yr method
    public static void previousYear(){
        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();

        for (Transaction transaction : allTransaction) {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String transactionDate = transaction.getDate();

            LocalDate localDate = LocalDate.parse(transactionDate, dateFormatter);

            int transactionYear = localDate.getYear();

            if (currentYear == transactionYear) {
                System.out.println(transaction);
            }
        }

    }

   public static void searchByVendor(){

       System.out.println("Placeholder: Search by vendor");

       System.out.println("Please provide the vendor name you're looking for...");
       System.out.print("Vendor: ");
       String nameToSearch = inputScanner.nextLine();

       for (int i = 0; i < allTransaction.size(); i++) {
           Transaction currentTransaction = allTransaction.get(i);
           if (currentTransaction.getVendor().equalsIgnoreCase(nameToSearch)) {
               System.out.println(currentTransaction);
           }
       }
   }
    public static void customSearch() {

        int customSearch;

        do {
            System.out.println(" Please select a custom search");
            System.out.println("1) Start Date");
            System.out.println("2) End Date");
            System.out.println("3) Description");
            System.out.println("4) Vendor");
            System.out.println("5) Amount");
            System.out.println("0) Go Back To Custom Search Page");

            try {
                customSearch = commandScanner.nextInt();
            } catch (InputMismatchException ime) {
                customSearch = 0;
            }

            switch (customSearch) {
                case 1:
                    startDate();
                    break;
                case 2:
                    endDate();
                    break;
                case 3:
                    description();
                    break;
                case 4:
                    vendor();
                    break;
                case 5:
                  //  amount();
                    break;
                case 0:
                    System.out.println("Going back to the main menu.");
                    break;
                default:
                    System.out.println("Command not found. Please try again.");
            }
            } while (customSearch != 0) ;
    }


    public static void startDate(){
        System.out.println("Please enter the start date (YYYY-MM-DD");


    }
    public static void endDate(){
        System.out.println("Please enter the end date");
    }

    public static void description(){
        System.out.println("Please enter the description");
    }

    public static void vendor(){
        System.out.println("Please enter the vendor");
        System.out.println("Placeholder: Search by vendor");

        System.out.println("Please provide the vendor name you're looking for...");
        System.out.print("Vendor: ");
        String nameToSearch = inputScanner.nextLine();

        for (int i = 0; i < allTransaction.size(); i++) {
            Transaction currentTransaction = allTransaction.get(i);
            if (currentTransaction.getVendor().equalsIgnoreCase(nameToSearch)) {
                System.out.println(currentTransaction);
            }
        }
    }




//class curly braces
}

        
        
        


   

