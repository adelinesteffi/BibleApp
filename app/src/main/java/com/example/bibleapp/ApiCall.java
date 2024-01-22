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
import java.util.concurrent.CountDownLatch;

import org.json.JSONException;
import org.json.JSONObject;
import javax.net.ssl.HttpsURLConnection;

public class ApiCall {

        String getdata(String book, String chapter, String verse) {
                final String[] formattedResult1 = new String[1];
                final CountDownLatch latch = new CountDownLatch(1);

                MyApp.executorService.execute(new Runnable() {
                        String jsonResponse;

                        @Override
                        public void run() {
                                HttpURLConnection httpURLConnection = null;
                                String urlString = "https://niv-bible.p.rapidapi.com/row?Book=" + book + "&Chapter=" + chapter + "&Verse=" + verse;
                                try {
                                        URL urlObj = new URL(urlString);
                                        httpURLConnection = (HttpURLConnection) urlObj.openConnection();
                                        httpURLConnection.setRequestProperty("X-RapidAPI-Key", "64f289dd38msh9c4fc34d7b959eap12e554jsn6f7fbc6396f6");
                                        httpURLConnection.setRequestProperty("X-RapidAPI-Host", "niv-bible.p.rapidapi.com");
                                        httpURLConnection.setRequestMethod("GET");
                                        InputStream inputStr = httpURLConnection.getInputStream();
                                        String encoding = httpURLConnection.getContentEncoding() == null ? "UTF-8"
                                                : httpURLConnection.getContentEncoding();
                                        StringBuilder buffer = new StringBuilder();
                                        int v;
                                        while ((v = inputStr.read()) != -1) {
                                                buffer.append((char) v);
                                        }
                                        jsonResponse = buffer.toString();
                                        Log.d("testing", "jsonResponse" + jsonResponse);
                                        String formattedResult = parseJson(jsonResponse);
                                        Log.d("testing", "formattedResult" + formattedResult);
                                        formattedResult1[0] = formattedResult;
                                } catch (MalformedURLException e) {
                                        e.printStackTrace();
                                } catch (IOException e) {
                                        System.out.println("There is an error");
                                        e.printStackTrace();
                                } finally {
                                        if (httpURLConnection != null) {
                                                httpURLConnection.disconnect();
                                        }
                                        // Signal that the operation is complete
                                        latch.countDown();
                                }
                        }
                });

                try {
                        // Wait for the network thread to complete
                        latch.await();
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }

                Log.d("testing", "formattedResult beore return" + formattedResult1[0]);
                return formattedResult1[0];
        }


        private static String parseJson(String jsonString) {
                try {
                        // Parse JSON string
                        JSONObject jsonObject = new JSONObject(jsonString);

                        // Get keys from the "Book" object
                        JSONObject bookObject = jsonObject.getJSONObject("Book");
                        java.util.Iterator<String> keys = bookObject.keys();

                        // Iterate over keys
                        while (keys.hasNext()) {
                                String key = keys.next();
                                String bookName = bookObject.getString(key);
                                int chapterNumber = jsonObject.getJSONObject("Chapter").getInt(key);
                                String text = jsonObject.getJSONObject("Text").getString(key);
                                int verseNumber = jsonObject.getJSONObject("Verse").getInt(key);

                                // Format the result
                                return String.format("Book: %s, Chapter: %d, Verse: %d, Text: %s",
                                        bookName, chapterNumber, verseNumber, text);
                        }

                        return "No matching keys found";
                } catch (JSONException e) {
                        throw new RuntimeException(e);
                }
        }
}
