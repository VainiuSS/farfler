package com.farfler.service;

import android.util.JsonReader;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.InputStreamReader;
import java.io.InputStream;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ApiService {

    public void getTextFromApi(String urlString) {
        try {
            // URL to fetch the JSON from the API
//            String urlString = "https://www.reddit.com/subreddits/search.json?q=blind";  // Replace with your URL
            HttpURLConnection connection = (HttpURLConnection) new URL(urlString).openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);  // Timeout in milliseconds
            connection.setReadTimeout(5000);     // Read timeout in milliseconds

            // Read the response
            InputStream inputStream = connection.getInputStream();

            // Convert the InputStream to a JsonObject using GSON
            JsonReader reader = new JsonReader(new InputStreamReader(inputStream));
//            JsonObject rootObject = JsonParser.parseReader(reader).getAsJsonObject();
            JsonParser parser = new JsonParser();
            JsonObject rootObject = parser.parse(String.valueOf(reader)).getAsJsonObject();

            // Get the "children" array from the JSON response
            JsonArray children = rootObject.getAsJsonObject("data").getAsJsonArray("children");

            Map<String, String> resultMap = new HashMap<>();

            // Iterate through the children array and extract data
            for (JsonElement childElement : children) {
                JsonObject childData = childElement.getAsJsonObject().getAsJsonObject("data");
                String title = childData.get("title").getAsString();
                String selfText = childData.get("selftext") != null ? childData.get("selftext").getAsString() : "";

                // Add extracted data to the map
                resultMap.put(title, selfText);
            }

            // Print the results
            for (Map.Entry<String, String> entry : resultMap.entrySet()) {
                System.out.println("Title: " + entry.getKey());
                System.out.println("Selftext: " + entry.getValue());
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
