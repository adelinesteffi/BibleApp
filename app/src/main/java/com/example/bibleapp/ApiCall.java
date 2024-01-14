package com.example.bibleapp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ApiCall extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {
        String urlString = params[0];

        try {
            URL urlObj = new URL(urlString);
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlObj.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("X-RapidAPI-Key", "2a4a421b7cmshcbc4460170ddc73p1d39c3jsnd619148a9fe2");
            httpURLConnection.setRequestProperty("X-RapidAPI-Host", "moviesdatabase.p.rapidapi.com");

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }

            reader.close();
            inputStream.close();
            httpURLConnection.disconnect();

            return builder.toString();

        } catch (IOException e) {
            Log.e("ApiRequestTask", "Error making API request: " + e.getMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(String jsonResponse) {
        // Handle the JSON response here
        if (jsonResponse != null) {
            Log.d("ApiRequestTask", "JSON Response: " + jsonResponse);
            // Parse and process the JSON as needed
        } else {
            Log.e("ApiRequestTask", "JSON Response is null");
        }
    }
}
