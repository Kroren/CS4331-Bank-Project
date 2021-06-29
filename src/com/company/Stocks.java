package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.Random;

public class Stocks {
    public static String[] names = {
            "TSLA",
            "AMZN",
            "AAPL",
            "NV",
            "FB",
            "WISH",
            "CLOV",
            "MRIN"
    };


    @SuppressWarnings("unchecked")
    public static void populateStocks() {
        try {
            Random r = new Random();
            JSONArray stockList = new JSONArray();

            for (String n : names) {
                System.out.println("Found stock " + n);
                JSONObject stockDetails = new JSONObject();
                double min = 0;
                double max = 10.50;
                double diff = max - min;

                double unitPrice = 0 + Math.random() * diff;

                int quantity = r.nextInt(50);
                int change = r.nextInt(20);

                stockDetails.put("unitPrice", unitPrice);
                stockDetails.put("quantity", quantity);
                stockDetails.put("change", change);

                JSONObject stockObject = new JSONObject();
                stockObject.put(n, stockDetails);
                stockList.add(stockObject);
            }
            System.out.println("Generating stocks...");
            FileWriter file = new FileWriter("Stocks.json");
            file.write(stockList.toJSONString());
            file.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
