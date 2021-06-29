package com.company;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.util.Base64;
import java.util.Scanner;


public class Main {
    ObjectInputStream inputStream = null;
    public static void main(String[] args) throws FileNotFoundException {

        if(!Encryption.keysPresent()) {
            // Check and make sure if a public key and private key already exist
            // Generate one otherwise.
            Encryption.generateKey();
            System.out.println("[DEBUG] No keys found, generated some!");
        }
        // Generate stocks
        Stocks.populateStocks();

        boolean exit = false;
        while (!exit) {
            menu();
        }
    }

    public static void menu() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Secure Bank of Springfield");
        System.out.println("+------------------------+");
        System.out.println("[1] Customer");
        System.out.println("[2] Bank Teller");

        System.out.println("Please Enter Selection: ");
        int user = sc.nextInt();
        // Create security object
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

    //static void generateStocks() { }

    static void customerLogin(Scanner s) throws FileNotFoundException {

        Customer customer = new Customer();

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
                        customer.displayMenu();
                    } else {
                        System.out.println("Incorrect Username or Password");
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
        // Register a new account.
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
            newAccount.profileWrite(newAccount);
            newAccount.bankWrite(newAccount);
            System.out.println("Account creation successful, Returning to Login");
            return;

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    static boolean login(String username, String password) throws FileNotFoundException {
        boolean isValid = true;
        // Parsing accounts. json file
        try {
            Object obj = new JSONParser().parse(new FileReader("Accounts.json"));

            // Typecasting to JSONObject
            JSONObject jo = (JSONObject) obj;

            String user = (String) jo.get("username");
            String pass = (String) jo.get("password");

            // convert back to a byte array
            //byte[] encodedPass = pass.getBytes(StandardCharsets.ISO_8859_1);
            byte[] decodedPass = Base64.getDecoder().decode(pass);
            System.out.println("[DEBUG] encoded pass = " + decodedPass);

            ObjectInputStream inputStream = null;
            inputStream = new ObjectInputStream(new FileInputStream(Encryption.PRIVATE_KEY_FILE));
            final PrivateKey privateKey = (PrivateKey) inputStream.readObject();
            final String plainText = Encryption.decrypt(decodedPass, privateKey);

            System.out.println("[DEBUG] Decrypted pass reads: " + plainText);


            // If the login is valid
            if (username.equals(user) & plainText.equals(password)) {

                // Login approved
                isValid = true;
            } else {
                System.out.println("Incorrect Username or Password");
                isValid = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

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
