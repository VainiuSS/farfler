package com.farfler.service;

import com.farfler.object.redditPost;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ApiService {

    public redditPost getTextFromApi(String urlString) {
        try {
            // URL to fetch the JSON from the API
            HttpURLConnection connection = (HttpURLConnection) new URL(urlString).openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);  // Timeout in milliseconds
            connection.setReadTimeout(5000);     // Read timeout in milliseconds

            // Read the response
            InputStream inputStream = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);

            // Convert the InputStream to a list of redditPost objects using GSON
            Gson gson = new Gson();
            redditPost redditPost = gson.fromJson(reader, redditPost.class);


            // Close the reader and connection
            reader.close();
            connection.disconnect();

            // Return the list of children (posts)
            return redditPost;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}