package com.example.bibleapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class VerseOfTheDayFragment  extends Fragment
{

        private TextView verseTextView;


        static   String VerseText ;
        static int color;
        public static VerseOfTheDayFragment newInstance(BibleModel verse) {
            VerseOfTheDayFragment ff = new VerseOfTheDayFragment();
            VerseText =verse.getfullVerseText();
          //  Log.d("testing", "VerseText"+VerseText);

            color    = verse.getBackGroundColor();

           // Log.d("testing", "icolor"+color);
            return ff;    }
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.fragment_verseoftheday, container, false);
            verseTextView = view.findViewById(R.id.questionTextView);
            verseTextView.setText(VerseText);
            verseTextView.setBackgroundResource(color);
          //  Log.d("testing", "icolor near set bvk grnd "+color);


            return view;
        }


    }


