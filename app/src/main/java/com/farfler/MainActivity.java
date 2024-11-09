package com.farfler;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.farfler.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private static final String API_URL = "https://www.reddit.com/r/redditdev/new.json?limit=3"; // Replace with your actual API URL

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ExecutorService for running background tasks
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> fetchDataFromApi(API_URL));
    }

    private void fetchDataFromApi(String urlString) {
        String result = "";
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        try {
            // Create URL object
            URL url = new URL(urlString);

            // Open connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(15000);  // 15 seconds timeout
            urlConnection.setReadTimeout(15000);     // 15 seconds timeout

            // Connect to the API
            urlConnection.connect();

            // Read the response
            InputStreamReader inputStreamReader = new InputStreamReader(urlConnection.getInputStream());
            reader = new BufferedReader(inputStreamReader);
            String line;
            StringBuilder stringBuilder = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            result = stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        // Once data is fetched, update the UI on the main thread
        String finalResult = result;
        runOnUiThread(() -> handleJsonResponse(finalResult));
    }

    private void handleJsonResponse(String result) {
        try {
            // Parse the JSON response
            JSONArray jsonArray = new JSONArray(result);

            // Find the layout where the buttons will be added
            LinearLayout layout = findViewById(R.id.fab); // Make sure this layout exists in your XML
            layout.removeAllViews(); // Remove any previous buttons

            // Iterate over the JSON array and create buttons for each item
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String title = jsonObject.getString("title");
                String text = jsonObject.getString("text");

                // Create a button dynamically
                Button button = new Button(MainActivity.this);
                button.setText(title);

                // Set an OnClickListener to read the title and text aloud
                button.setOnClickListener(v -> {
                    // Use Text-to-Speech or any other method to read aloud the title and text
                    String message = title + " " + text;
                    // Example Toast for demo purpose, replace with Text-to-Speech code
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                });

                // Add the button to the layout
                layout.addView(button);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}