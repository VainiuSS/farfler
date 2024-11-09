package com.farfler;

import android.os.Bundle;

import com.farfler.service.ApiService;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.speech.tts.TextToSpeech;
import android.util.Log;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private TextToSpeech textToSpeech;

    private final String jsonString = "{\n" +
            "  \"kind\": \"Listing\",\n" +
            "  \"data\": {\n" +
            "    \"children\": [\n" +
            "      {\n" +
            "        \"kind\": \"t3\",\n" +
            "        \"data\": {\n" +
            "          \"title\": \"Created a web app to transfer subreddit subscriptions across accounts\",\n" +
            "          \"author\": \"SubTransfer\",\n" +
            "          \"selftext\": \"It's called SubTransfer and it's a very simple app to carry over your subscriptions...\"\n" +
            "        }\n" +
            "      },\n" +
            "      {\n" +
            "        \"kind\": \"t3\",\n" +
            "        \"data\": {\n" +
            "          \"title\": \"Inconsistency with unsaving using PRAW\",\n" +
            "          \"author\": \"chaosboy229\",\n" +
            "          \"selftext\": \"Hi peeps, I'm trying to unsave a large number of my Reddit posts using the PRAW...\"\n" +
            "        }\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "}";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize TextToSpeech
        textToSpeech = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech.setLanguage(Locale.US);
                ApiService apiService = new ApiService();
                apiService.getTextFromApi("https://www.reddit.com/r/blind/top.json?t=month&limit=3&after=t3_1gkinlx");
                parseAndSpeakJSON(jsonString);
            } else {
                Toast.makeText(this, "TextToSpeech Initialization Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Parse JSON and speak the extracted content
    private void parseAndSpeakJSON(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONObject dataObject = jsonObject.getJSONObject("data");
            JSONArray childrenArray = dataObject.getJSONArray("children");

            StringBuilder contentToSpeak = new StringBuilder();
            for (int i = 0; i < childrenArray.length(); i++) {
                JSONObject childObject = childrenArray.getJSONObject(i).getJSONObject("data");

                // Extract title, author, and selftext
                String title = childObject.optString("title", "No Title");
                String author = childObject.optString("author", "Unknown Author");
                String selftext = childObject.optString("selftext", "No content available");

                contentToSpeak.append("Post ").append(i + 1).append(": ")
                        .append("Title: ").append(title).append(". ")
                        .append("Author: ").append(author).append(". ")
                        .append("Content: ").append(selftext).append(".\n\n");
            }

            speakContent(contentToSpeak.toString());

        } catch (JSONException e) {
            Log.e("JSONError", "Failed to parse JSON", e);
            Toast.makeText(this, "Failed to parse JSON", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to speak the content
    private void speakContent(String content) {
        textToSpeech.speak(content, TextToSpeech.QUEUE_FLUSH, null, "UniqueID");
    }

    @Override
    protected void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}
