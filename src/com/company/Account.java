package com.company;
import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// The class whcih will create account objects to store in the json
public class Account {
    String username;
    String password;
    boolean isTeller;
    // Bank information below
    String SSN = "666-420-6969"; // Just a placeholder
    double income = 1337.00;
    String phoneNum = "760-706-7424";
    String email = "JohnDoe@Gmail.com";
    String name;


    double balance = 1337.00;

    Account(String username, String password, String name, boolean isTeller) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader("Accounts.json"));

        this.username = username;
        this.password = password;
        this.name = name;
        this.isTeller = isTeller;

        // Call json object
        // convert to proper format
        // encrypt and secure
        // Write
    }
    @SuppressWarnings("unchecked")
    public void accountWrite(Account acct) throws FileNotFoundException {
        // Json writer isn't working entirely correctly.
        JSONObject accountDetails = new JSONObject();
        // The global account list
        JSONArray accountList = new JSONArray();

        accountDetails.put("username", acct.username);
        accountDetails.put("password", acct.password);
        accountDetails.put("isTeller", acct.isTeller);
        // This account

        try (FileWriter file = new FileWriter("accounts.json")) {
            // Write the json information to the file.
            file.write(accountDetails.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
