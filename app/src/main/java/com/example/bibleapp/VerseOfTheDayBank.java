package com.example.bibleapp;

import android.content.Context;

import java.util.ArrayList;

public class VerseOfTheDayBank {
    ArrayList<BibleModel> verseOfTheDayList;

    public ArrayList<BibleModel> getQuestionList(Context context) {
        if (verseOfTheDayList == null) {

            verseOfTheDayList = new ArrayList<BibleModel>(10);

            verseOfTheDayList.add(new BibleModel("Psalm 112:4","Even in darkness light dawns for the upright, for those who are gracious and compassionate", R.color.color1));

            verseOfTheDayList.add(new BibleModel( "John 14:23", "Keep my Word",R.color.color2));
          //  verseOfTheDayList.add(new BibleModel(context.getResources().getString(R.string.Verse3), R.color.color3, false));
           // verseOfTheDayList.add(new BibleModel(context.getResources().getString(R.string.Verse4), R.color.color4, false));
           // verseOfTheDayList.add(new BibleModel(context.getResources().getString(R.string.Verse5), R.color.color5, true));

        }
        return verseOfTheDayList;
    }
}
