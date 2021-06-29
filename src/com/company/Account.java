package com.company;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Base64;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


// The class which will create account objects to store in the json
public class Account {
    String username;
    String password;
    boolean isTeller;
    // Bank information below
    String ssn = "666-420-6969"; // Just a placeholder
    double income = 1337.00;
    String phoneNum = "760-706-7424";
    String email = "JohnDoe@Gmail.com";
    String name;
    String address = "123 69th St";

    int accountNumber = 696969;
    int checking = 1000;
    int savings = 1000;



    double balance = 1337.00;

    Account(String username, String password, String name, boolean isTeller) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();

        this.username = username;
        this.password = password;
        this.name = name;
        this.isTeller = isTeller;

    }
    @SuppressWarnings("unchecked")
    public void accountWrite(Account acct) throws FileNotFoundException {
        try {
            // Save the info here
            JSONObject accountDetails = new JSONObject();

            // Encrypt the password
            ObjectInputStream inputStream = null;
            inputStream = new ObjectInputStream(new FileInputStream(Encryption.PUBLIC_KEY_FILE));
            final PublicKey publicKey = (PublicKey) inputStream.readObject();
            final byte[] encryptedPassword = Encryption.encrypt(acct.password, publicKey);

            // Trying to make a format that works for json
            String decodedPass = new String(encryptedPassword, StandardCharsets.ISO_8859_1);

            accountDetails.put("username", acct.username);
            accountDetails.put("password", decodedPass);
            accountDetails.put("isTeller", acct.isTeller);
            // This account

            FileWriter file = new FileWriter("accounts.json");
            // Write the json information to the file.
            file.write(accountDetails.toJSONString());
            file.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    public void profileWrite(Account acct) throws FileNotFoundException {
        JSONObject profileDetails = new JSONObject();
        // The global account list
        // JSONArray accountList = new JSONArray();

        profileDetails.put("name", acct.name);
        profileDetails.put("ssn", acct.ssn);
        profileDetails.put("income", acct.income);
        profileDetails.put("phone number", acct.phoneNum);
        profileDetails.put("email", acct.email);
        profileDetails.put("address", acct.address);

        // This account

        try (FileWriter file = new FileWriter("profile.json")) {
            // Write the json information to the file.
            file.write(profileDetails.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void bankWrite(Account acct) throws FileNotFoundException {
        JSONObject bankDetails = new JSONObject();

        bankDetails.put("account number", acct.accountNumber);
        bankDetails.put("checking", acct.checking);
        bankDetails.put("savings", acct.savings);

        try (FileWriter file = new FileWriter("bank.json")) {
            // Write the json information to the file.
            file.write(bankDetails.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
