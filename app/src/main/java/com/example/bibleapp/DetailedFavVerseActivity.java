package com.example.bibleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailedFavVerseActivity extends AppCompatActivity {
    TextView detailFav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_fav_verse);

        detailFav = findViewById(R.id.FavDetailedTextView);
        Intent intent = getIntent();
        if (intent != null) {
            String receivedValue = intent.getStringExtra("detail");
            if (receivedValue != null) {
                // Now you can use the receivedValue in your activity
                detailFav.setText(receivedValue);
            }
        }
    }
}