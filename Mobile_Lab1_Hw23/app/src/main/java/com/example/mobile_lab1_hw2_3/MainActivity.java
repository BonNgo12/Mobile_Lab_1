package com.example.mobile_lab1_hw2_3;

import android.os.AsyncTask;
import android.os.Bundle;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private EditText etInput;
    private Button btnSubmit;
    private TextView tvResult;
    private LinearLayout layout;
    private static final String apiKey = "";

    private static final String apiUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=" + apiKey;
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInput = findViewById(R.id.etInput);
        btnSubmit = findViewById(R.id.btnSubmit);
        tvResult = findViewById(R.id.tvResult);
        layout = (LinearLayout) findViewById(R.id.tvTitle).getParent();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = etInput.getText().toString().trim();
                if (!text.isEmpty()) {
                    new analyzeSentiment().execute(text);
                } else {
                    tvResult.setText("Please enter a sentence.");
//                    emotionIcon.setImageResource(0);
                }
            }
        });
    }

    private class analyzeSentiment extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String sentence = params[0];
            OkHttpClient client = new OkHttpClient();

            String prompt = "Classify the sentiment of this sentence as positive, negative, or neutral and return only one of these words: 'positive', 'negative', or 'neutral'. The sentence is in English or Vietnamese. Always ensure proper handling of Vietnamese text encoding. Sentence: '" + sentence + "'";

            String jsonBody = "{\"contents\": [{\"parts\": [{\"text\": \"" + prompt + "\"}]}]}";

            RequestBody body = RequestBody.create(jsonBody, JSON);
            Request request = new Request.Builder()
                    .url(apiUrl)
                    .post(body)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    JSONObject json = new JSONObject(responseBody);
                    return json.getJSONArray("candidates")
                            .getJSONObject(0)
                            .getJSONObject("content")
                            .getJSONArray("parts")
                            .getJSONObject(0)
                            .getString("text")
                            .trim()
                            .toLowerCase();
                } else {
                    return "error";
                }
            } catch (Exception e) {
                return "error";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            tvResult.setText(result.equals("error") ? "Lỗi phân loại" : result);

            // Display corresponding icon
            switch (result) {
                case "positive":
                    tvResult.setText("😊");
                    layout.setBackgroundColor(Color.GREEN);
                    break;
                case "negative":
                    tvResult.setText("☹️");
                    layout.setBackgroundColor(Color.RED);
                    break;
                default:
                    tvResult.setText("😐");
                    layout.setBackgroundColor(Color.YELLOW);
                    break;
            }
        }
    }
}