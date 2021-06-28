package com.company;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        boolean exit = false;
        while (!exit) {
            menu();
        }
    }

    public static void menu() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Secure Bank of Uganda");
        System.out.println("+------------------------+");
        System.out.println("[1] Customer");
        System.out.println("[2] Bank Teller");

        System.out.println("Please Enter Selection: ");
        int user = sc.nextInt();

        switch (user)
        {
            case 1:
                customerLogin(sc);
                break;
            case 2:
                System.out.println("teller Login comming");
                break;
        }
    }

    static void customerLogin(Scanner s) throws FileNotFoundException {
        // Created customer class object
        System.out.println("\n\nWelcome Customer");
        System.out.println("-----------------");
        System.out.println("[1] Login \n[2] Register \n[3] Exit");

        int choice = s.nextInt();

        switch (choice) {
            case 1:
                // Login here
                System.out.print("\nUsername: ");
                String username = s.next();
                System.out.print("Password: ");
                String password = s.next();
                // Might add password masking later.
                try {
                    boolean isLoggedIn = login(username, password);
                    if (isLoggedIn) {
                        System.out.println("logged in");
                        // Go to customer panel
                    } else {
                        System.out.println("access Denied");
                        // Deny access
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                customerRegister(s);
                break;
            case 3:
                break;
        }
    }

    static void customerRegister(Scanner s) {
        System.out.println("Welcome!  Please fill out a short questionaire in order fill out your bank account details with us.\n");

        System.out.print("Username: ");
        String username = s.next();

        System.out.print("Password: ");
        String password = s.next();

        System.out.println("What is your full name?");
        String fullName = s.next();

        System.out.println("I'm too lazy to actually fill out the rest of the prompt, so using all the filler info.");
        try {
            Account newAccount = new Account(username, password, fullName, false);
            newAccount.accountWrite(newAccount);
            System.out.println("Account creation successful, Returning to Login");
            return;

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    static boolean login(String username, String password) throws FileNotFoundException {
        boolean isValid = true;
        // Parsing accounts. json file
        Object obj = null;
        try {
            obj = new JSONParser().parse(new FileReader("Accounts.json"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        // Typecasting to JSONObject
        JSONObject jo = (JSONObject) obj;

        String user = (String) jo.get("username");
        String pass = (String) jo.get("password");

        System.out.println("[DEBUG] Found username: " + user);
        System.out.println("[DEBUG] Found password: " + pass);

        return isValid;
    }

    static void tellerLogin(Scanner s) throws FileNotFoundException {
        // Created bank teller class object
        System.out.println("\n\nWelcome Bank Teller");
        System.out.println("-----------------");
        System.out.println("[1] Login \n[2] Register \n[3] Exit");

        int choice = s.nextInt();

        switch (choice) {
            case 1:
                // Login here
                System.out.print("\nUsername: ");
                String username = s.next();
                System.out.print("Password: ");
                String password = s.next();
                // Might add password masking later.
                try {
                    boolean isLoggedIn = login(username, password);
                    if (isLoggedIn) {
                        System.out.println("logged in");
                        // Go to bank teller panel
                    } else {
                        System.out.println("access Denied");
                        // Deny access
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                // banktller register if you want?
                break;
            case 3:
                break;
        }
    }
}
