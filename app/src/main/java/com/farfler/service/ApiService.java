package com.farfler.service;

import com.farfler.object.redditPost;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ApiService {

    public List<redditPost> getTextFromApi(String urlString) {
        try {
            // URL to fetch the JSON from the API
            HttpURLConnection connection = (HttpURLConnection) new URL(urlString).openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);  // Timeout in milliseconds
            connection.setReadTimeout(5000);     // Read timeout in milliseconds

            // Read the response
            InputStream inputStream = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);

            // Convert the InputStream to a RedditListing object using GSON
            Gson gson = new Gson();
            Type redditPostListType = new TypeToken<List<redditPost>>() {}.getType();
            List<redditPost> redditPosts = gson.fromJson(reader, redditPostListType);

            // Close the reader and connection
            reader.close();
            connection.disconnect();

            // Return the list of children (posts)
            return redditPosts;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}