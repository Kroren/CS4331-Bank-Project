package com.company;

import java.util.Scanner;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;



public class Customer {

    public void displayMenu() throws FileNotFoundException {

        Scanner in = new Scanner(System.in);
        boolean quite = false;
        do {
            System.out.println(" ");
            System.out.println("**************************");
            System.out.println("Customer Menu");
            System.out.println("[1] View Profile");
            System.out.println("[2] Query Account");
            System.out.println("[3] Transfer Funds");
            System.out.println("[4] Query Stock");
            System.out.println("[5] Buy Stock");
            System.out.println("[6] Sell Stock");
            System.out.println("[7] Log Out");
            System.out.println("Please Enter Selection: ");
            int choice = in.nextInt();

            switch (choice)
            {
                case 1:
                    viewProfile();
                case 2:
                    //queryAccount();
                case 3:
                    //transfer funds
                case 4:
                    //query stock
                case 5:
                    //buy stock
                case 6:
                    //sell stock
                case 7:
                    logOut();
            }
        }
        while(true);
    }



     public void viewProfile() throws FileNotFoundException {

        //get customer information from profile.json
         Object obj = null;
         try {
             obj = new JSONParser().parse(new FileReader("profile.json"));
         } catch (IOException e) {
             e.printStackTrace();
         } catch (ParseException e) {
             e.printStackTrace();
         }
         JSONObject jo = (JSONObject) obj;
         double Income = (double) jo.get("income");
         String Name = (String) jo.get("name");
         String Phone = (String) jo.get("phone number");
         String Email = (String) jo.get("email");
         String SSN = (String) jo.get("ssn");
         String Address = (String) jo.get("address");


         Scanner s = new Scanner(System.in);
         System.out.println("Please Enter Customer name: ");
         String name = s.next();
         System.out.println("Please Enter Customer SSN in the Following Format ___-___-____ : ");
         String ssn = s.next();

         //Name and ssn validation
         if (name.equals(Name) && ssn.equals(SSN)) {
             System.out.println("Name: " + Name);
             System.out.println("SSN: " + SSN);
             System.out.println("Address: " + Address);
             System.out.println("Phone Number: " + Phone);
             System.out.println("Income: $"+Income);
             System.out.println("Email: " + Email);
         }
         else {
             System.out.println("Incorrect Name or SSN");
         }

     }


     //Not working need to read integers from json file
     public void queryAccount() throws FileNotFoundException {

        //get banking information from bank.json
        Object obj = null;
         try {
             obj = new JSONParser().parse(new FileReader("bank.json"));
         } catch (IOException e) {
             e.printStackTrace();
         } catch (ParseException e) {
             e.printStackTrace();
         }
         JSONObject jo = (JSONObject) obj;
         int accountNumber = (int) jo.get("account number");
         int checking = (int) jo.get("checking");
         int savings = (int) jo.get("savings");

         Scanner s = new Scanner(System.in);
         System.out.println("Please Enter Account Number: ");
         int accNumber = s.nextInt();

         if (accNumber == accountNumber) {
             System.out.println("Checking Account: $"+checking);
             System.out.println("Savings Account: $"+savings);
         }
         else {
             System.out.println("Incorrect Account Number");
         }


     }


    public void transferFunds() {

        //get values from bank file
        Scanner s = new Scanner(System.in);
        System.out.println("Please Enter Amount To Transfer: ");
        int amount = s.nextInt();
        System.out.println("Which Account Would You Like to Transfer From? Checking or Savings: ");
        String choice = s.next();

        if (choice.equals("Checking")) {
            System.out.println("Transferring " + amount + "to Savings");
            //application logic (subtract amount and set new amounts)
        }
        else if (choice.equals("Savings")) {
            System.out.println("Transferring " + amount + "to Checking");
            //application logic (subtract amount and set new amounts)
        }
        else {
            System.out.println("Invalid Entry Please Try Again");
        }
//        write new values to file
//        JSONObject bankDetails = new JSONObject();
//
//
//        bankDetails.put("checking", acct.checking);
//        bankDetails.put("savings", acct.savings);
//
//        try (FileWriter file = new FileWriter("bank.json")) {
//            // Write the json information to the file.
//            file.write(bankDetails.toJSONString());
//            file.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    }

    //public void queryStocks(String customerName) {}

    //public void buyStock(String stockName, int quantity, double price, int accountNumber) {}

    //public void sellStock(String stockName, int quantity, double price, int accountNumber) {}

    public void logOut() {

        Scanner s = new Scanner(System.in);
        System.out.println("Are you Sure You Want To Exit [1]Yes or [2]No?");
        int choice = s.nextInt();

        if (choice == 1 ) {
            System.out.println("Thank You For Choosing the Secure Bank of Uganda Goodbye");
            System.exit(0);
        }
        else if (choice ==  2) {
            return;
        }
        else {
            System.out.println("Invalid Input");
        }
    }


}