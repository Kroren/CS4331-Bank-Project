package com.company;

import java.util.Scanner;

public class Customer {
    // we don't need to specify the type of menu since they already exist within that class
    // i.e. Customer.displayMenu
    public void displayMenu() {

        Scanner in = new Scanner(System.in);

        System.out.println("**************************");
        System.out.println("Customer Menu");
        System.out.println("**************************");
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
                //view profile method
            case 2:
                //query account
            case 3:
                //transfer funds
            case 4:
                //query stock
            case 5:
                //buy stock
            case 6:
                //sell stock
            case 7:
                //logout
        }
    }





    // public void viewProfile(String name, String SSN) {}

    // public void queryAccount(int accountNumber) {}

    //public void transferFunds( int amount, String fromAccount, String toAccount) {}

    //public void queryStocks(String customerName) {}

    //public void buyStock(String stockName, int quantity, double price, int accountNumber) {}

    //public void sellStock(String stockName, int quantity, double price, int accountNumber) {}

    //public void logOut() {}


}