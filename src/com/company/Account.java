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
    String name;
    boolean isTeller;
    int SSN = 433222100; // Just a placeholder

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

        accountDetails.put("password", acct.password);
        accountDetails.put("name", acct.name);
        accountDetails.put("isTeller", acct.isTeller);
        // This account
        JSONObject account = new JSONObject();
        account.put(acct.username, accountDetails);

        accountList.add(account);

        try (FileWriter file = new FileWriter("accounts.json")) {
            // Write the json information to the file.
            file.write(accountList.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
