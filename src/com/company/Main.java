package com.company;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
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

        System.out.println("Welcome to bank of Uganda");
        System.out.println("+------------------------+");
        System.out.println("1) Customer \n 2) Bank Teller");

        int user = sc.nextInt();

        customerLogin(sc);

        /*switch (user) {
            case 1:
                customerLogin(sc);
            case 2:
                tellerLogin(sc);
            default:
                break;*/
    }

    static void customerLogin(Scanner s) throws FileNotFoundException {
        System.out.println("\n\n Welcome customer");
        System.out.println("-----------------");
        System.out.println("[1] Login \n [2] Register \n [3] Exit");

        int choice = s.nextInt();

        switch (choice) {
            case 1:
                // Login here
            case 2:
                customerRegister(s);
                break;
            case 3:
                break;
        }
    }

    static void customerRegister(Scanner s) {
        System.out.println("Username: ");
        String username = s.next();
        System.out.println("Password: ");
        String password = s.next();
        System.out.println("What is your full name?");
        String fullName = s.next();
        try {
            Account newAccount = new Account(username, password, fullName, false);
            newAccount.accountWrite(newAccount);
            System.out.println("Account creation successful, Returning to Login");
            return;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
