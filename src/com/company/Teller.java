package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.security.PublicKey;
import java.text.DecimalFormat;
import java.util.Base64;
import java.util.Random;
import java.util.Scanner;

public class Teller {


    public void displayMenu() throws IOException, ParseException {

        Scanner input = new Scanner(System.in);

        boolean quite = false;
        do {
            System.out.println(" ");
            System.out.println("**************************");
            System.out.println("Bank Teller Menu");
            System.out.println("[1] View Profile");
            System.out.println("[2] Query Account");
            System.out.println("[3] Withdraw Funds");
            System.out.println("[4] Query Stock");
            System.out.println("[5] Log Out");

            System.out.println("Please Enter Selection: ");
            int tellerChoice = input.nextInt();

            switch (tellerChoice)
            {
                case 1:
                    viewProfile();
                    break;
                case 2:
                    queryAccount();
                    break;
                case 3:
                    withdrawFunds();
                    break;
                case 4:
                    queryStocks();
                    break;
                case 5:
                    logOut();
                    break;
                default:
                    displayMenu();
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
        long accountNumber = (long) jo.get("account number");
        double checking = (double) jo.get("checking");
        double savings = (double) jo.get("savings");

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

    public void withdrawFunds() throws IOException, ParseException {


        JSONObject bankDetails = new JSONObject();
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
        long accountNumber = (long) jo.get("account number");
        double checkingAmount = (double) jo.get("checking");
        double savingsAmount = (double) jo.get("savings");
        //int accountNumber = (int) jo.get("account number");

        Scanner s = new Scanner(System.in);
        System.out.println("Please Enter Amount To Withdraw in Exact Change: ");
        double transferAmount = s.nextDouble();
        System.out.println("Which Account Would You Like to Withdraw From? [1]Checking or [2]Savings: ");
        int choice = s.nextInt();

        if (choice == 1) {
            if (checkingAmount > transferAmount) {
                System.out.println("Withdrawing $"+transferAmount);
                double newChecking = checkingAmount - transferAmount;

                double newSavings = savingsAmount;

                bankDetails.put("checking", newChecking);
                bankDetails.put("savings", newSavings);
                bankDetails.put("account number", accountNumber);

            }
            else {
                System.out.println("Insufficient Funds");
            }
        }
        else if (choice == 2) {
            if (savingsAmount > transferAmount) {
                System.out.println("Withdrawing $" + transferAmount);
                double newSavings = savingsAmount - transferAmount;

                double newChecking = checkingAmount;

                bankDetails.put("checking", newChecking);
                bankDetails.put("savings", newSavings);
                bankDetails.put("account number", accountNumber);

            }
            else {
                System.out.println("Insufficient Funds");
            }
        }
        else {
            System.out.println("Invalid Entry Please Try Again");
        }


        try (FileWriter file = new FileWriter("bank.json")) {
            // Write the json information to the file.
            file.write(bankDetails.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void queryStocks() {
        DecimalFormat formatter = new DecimalFormat("#0.00");
        JSONParser parser = new JSONParser();


        System.out.println("+------ Stock Exchange ------+");
        try {
            FileReader reader = new FileReader("Stocks.json");
            Object obj = parser.parse(reader);
            JSONArray stockArray = (JSONArray) obj;
            Random r = new Random();

            for (int i = 0; i < stockArray.size(); i++) {
                JSONObject stockList = (JSONObject) stockArray.get(i);
                String currentStock = Stocks.names[i];
                JSONObject stock = (JSONObject) stockList.get(currentStock);

                int coinFlip = r.nextInt(2);
                String flip;

                if (coinFlip == 1) {
                    flip = "+";
                } else {
                    flip = "-";
                }

                System.out.print(currentStock);
                System.out.print(" | PRICE " + formatter.format(stock.get("unitPrice")));
                System.out.print(" | QUANTITY " + stock.get("quantity"));
                System.out.print(" | %CHANGE " + flip + stock.get("change") + "%" + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logOut() {

        Scanner s = new Scanner(System.in);
        System.out.println("Are you Sure You Want To Exit [1]Yes or [2]No?");
        int choice = s.nextInt();

        if (choice == 1 ) {
            System.out.println("Thank You For Choosing the Secure Bank of Springfield Goodbye");
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