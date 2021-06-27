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

        customerLogin();

        /*switch (user) {
            case 1:
                customerLogin(sc);
            case 2:
                tellerLogin(sc);
            default:
                break;*/
    }

    static void customerLogin() throws FileNotFoundException {
        System.out.println("\n\n Welcome customer");
        System.out.println("-----------------");
        System.out.println("[1] Login \n [2] Register \n [3] Exit");

        //int choice = s.nextInt();

        // Test account case:
        String testUsername = "poggers";
        String testPass = "supergam,er";
        String name = "John Fortnite";

        try {
            Account testAcct = new Account(testUsername, testPass, name, false);
            testAcct.accountWrite(testAcct);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    static void tellerLogin(Scanner s) {
        // If the user logs in as a bank teller
    }
}
