package com.company;

import java.util.Scanner;

public class Teller {

    public void displayMenu() {

        Scanner input = new Scanner(System.in);

        System.out.println("**************************");
        System.out.println("Bank Teller Menu");
        System.out.println("[1] View Profile");
        System.out.println("[2] Query Account");
        System.out.println("[3] Withdraw Funds");
        System.out.println("[4] Query Stock");

        System.out.println("Please Enter Selection: ");
        int tellerChoice = input.nextInt();

        switch (tellerChoice)
        {
            case 1:
                //view profile method
            case 2:
                //query account
            case 3:
                //Withdraw funds
            case 4:
                //query stock

        }
    }


    // public void viewProfile(String name, String SSN) {}

    // public void queryAccount(int accountNumber) {}

    //public void withdrawFunds(int amount, int customerAccount) {}

    //public void queryStocks(String customerName) {}
}