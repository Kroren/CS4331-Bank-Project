package com.company;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Teller {

    public void displayMenu() throws FileNotFoundException {

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
                case 2:
                    //query account
                case 3:
                    //Withdraw funds
                case 4:
                    //query stock
                case 5:
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

    // public void queryAccount(int accountNumber) {}

    //public void withdrawFunds(int amount, int customerAccount) {}

    //public void queryStocks(String customerName) {}

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