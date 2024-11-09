package com.farfler;

import android.os.Bundle;

import com.farfler.object.redditPost;
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
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private TextToSpeech textToSpeech;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize TextToSpeech
        textToSpeech = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech.setLanguage(Locale.US);
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(() -> {
                    ApiService apiService = new ApiService();
                    redditPost redditPost = apiService.getTextFromApi("https://www.reddit.com/r/blind/top.json?t=month&limit=1");
                    if (redditPost != null) {
                        String selftext = redditPost.getData().getChildren().get(0).getData().getSelftext();
                        runOnUiThread(() -> speakContent(selftext));
                    }
                });
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

            speakContent(jsonString);

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
