package com.example.bibleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FrameLayout frameLayout;
    VerseOfTheDayFragment check_fragment;
    VerseOfTheDayBank verseListObj=new VerseOfTheDayBank();
    private ArrayList<BibleModel> verseList;
    int currentIndex=0;
    ApiCall apicallObj = new ApiCall();
    Button findBibleVerse , favBibleVerses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout = findViewById(R.id.framelayout);
        findBibleVerse= findViewById(R.id.viewBibleVerseBtn);
        findBibleVerse.setOnClickListener(this);
        favBibleVerses= findViewById(R.id.favouriteBtn);
        favBibleVerses.setOnClickListener(this);
         check_fragment = (VerseOfTheDayFragment) getSupportFragmentManager().findFragmentById(R.id.framelayout);
//        if (check_fragment != null) {
//            Log.d("testing", "oncreate  if");
//            getSupportFragmentManager().beginTransaction().remove(check_fragment).commit();
//        }
        verseList = verseListObj.getQuestionList(this);
//        check_fragment = check_fragment.newInstance(verseList.get(0));
//        getSupportFragmentManager().beginTransaction().add(R.id.framelayout, check_fragment).commit();
     startLoopAfterDelay(2000);
      //  apicallObj.getdata();
    }

    private void startLoopAfterDelay(int delayMillis) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Display the content in the FrameLayout
                displayContent();
                // Move to the next index
                currentIndex++;
                // Check if the loop should continue
//                    if (currentIndex >= verseList.size()) {
//                        // Reset currentIndex to 0 to repeat the loop
//                        currentIndex = 0;
//                    }
                if (currentIndex < verseList.size()) {
                    // Continue the loop with the next delay
                    startLoopAfterDelay(delayMillis); // 20 seconds (in milliseconds)
                }
            }
        }, delayMillis);
    }

    private void displayContent() {
        // Update the FrameLayout with the content from the ArrayList
        if (currentIndex < verseList.size()) {
//            //String currentItem = arrayList.get(currentIndex);
//            TextView textView = new TextView(this);
//         //   textView.setText(currentItem);
//            frameLayout.removeAllViews();
//            frameLayout.addView(textView);

            if (check_fragment != null) {
         //       Log.d("testing", "oncreate  if");
                getSupportFragmentManager().beginTransaction().remove(check_fragment).commit();
            }
            //verseList = verseListObj.getQuestionList(this);
            check_fragment = check_fragment.newInstance(verseList.get(currentIndex));
            getSupportFragmentManager().beginTransaction().add(R.id.framelayout, check_fragment).commit();
        }
    }


    @Override
    public void onClick(View view) {
      //  Log.d("testing", "in onclick");

        Button clickedButton = (Button) view;
        String buttonText = clickedButton.getText().toString();
        if (clickedButton == findBibleVerse)
        {
            Intent findBibleVerseIntent = new Intent(MainActivity.this, viewBibleVerse.class);
            startActivity(findBibleVerseIntent);

        }

        if (clickedButton == favBibleVerses)
        {
            Intent FavouriteVersesIntent = new Intent(MainActivity.this, FavouriteVersesListActivity.class);
            startActivity(FavouriteVersesIntent);

        }
    }
}