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
        JSONArray infoArray = (JSONArray)obj;

        this.username = username;
        this.password = password;
        this.name = name;
        this.isTeller = isTeller;

        // Call json object
        // convert to proper format
        // encrypt and secure
        // Write
    }

    public void accountWrite(Account acct) throws FileNotFoundException {
        // Json writer isn't working entirely correctly.
        JSONParser jsonParser = new JSONParser();
        try {
            Object obj = jsonParser.parse(new FileReader("accounts.json"));
            JSONArray infoArray = (JSONArray)obj;
            JSONArray parentArray = (JSONArray)obj;
            JSONObject user = new JSONObject();

            System.out.println(infoArray);

            JSONObject accountInfo = new JSONObject();
            accountInfo.put("username", acct.username);
            accountInfo.put("password", acct.password);


            infoArray.add(accountInfo);

            user.put(acct.username, infoArray);

            System.out.println(infoArray);

            FileWriter file = new FileWriter("accounts.json");
            file.write(infoArray.toJSONString());
            file.flush();
            file.close();

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }
}
