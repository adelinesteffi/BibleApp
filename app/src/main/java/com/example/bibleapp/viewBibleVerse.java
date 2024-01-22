package com.example.bibleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class viewBibleVerse extends AppCompatActivity  implements View.OnClickListener{
    Button getVerseBtn,resetBtn;
    ArrayList<BibleItem> BibleItemList;
    BibleItem bibleItem;
    EditText bookEdittext, chapterEditText,VerseEditText;
    TextView verseTextView;
    ToggleButton addToFavToggle;
String bookInput, ChapterInput,VerseInput, VerseText ;
Boolean isfav= false;
    ApiCall apicallObj = new ApiCall();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bible_verse);
        Log.d("Testing", "Inside onCreate viewBibleVerse");  // Add this li
        getVerseBtn= findViewById(R.id.btnGetVerse);
        getVerseBtn.setOnClickListener(this);
        resetBtn= findViewById(R.id.reset);
        resetBtn.setOnClickListener(this);
        bookEdittext = findViewById(R.id.editTextBook);
        bookEdittext.setOnClickListener(this);
        chapterEditText= findViewById(R.id.editTextChapter);
        chapterEditText.setOnClickListener(this);
        VerseEditText= findViewById(R.id.editTextVerse);
        VerseEditText.setOnClickListener(this);
        verseTextView= findViewById(R.id.textViewVerse);
        verseTextView.setOnClickListener(this);
        addToFavToggle= findViewById(R.id.toggleButtonFavorite);
        addToFavToggle.setOnClickListener(this);
        Log.d("Testing", "Inside onCreate viewBibleVerse end" +
                "s of on creatw");  // Add this li
    }

    @Override
    public void onClick(View view) {
        Log.d("testing", "in onclick view viblr verse class");
        Log.d("Testing", "Inside onClick viewBibleVerse");  // Add this li
        Button clickedButton = (Button) view;
        String buttonText = clickedButton.getText().toString();
        Log.d("buttonText", "in buttonText " + buttonText);
        if (clickedButton == resetBtn) {
            Log.d("testing", "in reset btn clear");
            bookEdittext.setText("");
            chapterEditText.setText("");
            VerseEditText.setText("");
            verseTextView.setText("");
            addToFavToggle.setChecked(false);
        }
        else  if (clickedButton == getVerseBtn)
        {
            Log.d("testing", "in else of reset btn");
            bookInput= bookEdittext.getText().toString();
            ChapterInput=chapterEditText.getText().toString();
            VerseInput=VerseEditText.getText().toString();
            if (isEditTextValid(bookEdittext) && isEditTextNumeric(chapterEditText) && isEditTextNumeric(VerseEditText))
            {
                // The EditText is valid, you can proceed with further actions
               VerseText =  apicallObj.getdata(bookInput,ChapterInput,VerseInput);

                Log.d("testing", "after apicallObj");
                verseTextView.setText(VerseText);
                Log.d("testing", VerseText+" VerseText");
                // Check if the toggle button is in the "on" state
                boolean addToFavChecked = addToFavToggle.isChecked();

                if (addToFavChecked ) {
                    Date savedTime= Calendar.getInstance().getTime();
                    bibleItem=new BibleItem(bookInput,ChapterInput,VerseInput,VerseText,savedTime.toString(),true);
                    ((MyApp) getApplication()).appBibleList.add(bibleItem);
                    Log.d("testing",  ((MyApp) getApplication()).appBibleList.size()+"  ((MyApp) getApplication()).appBibleList size");
                    Toast.makeText(this, "Added to favourite", Toast.LENGTH_SHORT).show();

                    for (BibleItem bibleItem :   ((MyApp) getApplication()).appBibleList) {
                        System.out.println(bibleItem.toString());

                        Log.d("testing",  bibleItem.toString()+"  bibleItem.toString()");
                        // Alternatively, print specific attributes if BibleItem has getters for them
                        // System.out.println("Book: " + bibleItem.getBook() + ", Chapter: " + bibleItem.getChapter() + ", Verse: " + bibleItem.getVerse());
                    }
                    // Toggle button is ON
                    // Perform actions when the toggle button is ON
                } else {
                    // Toggle button is OFF
                    // Perform actions when the toggle button is OFF
                }

                // Your additional logic...
            } else {
                // The EditText is not valid, handle the case accordingly
                // For example, display an error message or take another action
                Toast.makeText(this, "All fields are required and need to be valid", Toast.LENGTH_SHORT).show();
            }



        }
        else
        {

            Toast.makeText(this, "All fields are required and need to be valid", Toast.LENGTH_SHORT).show();
        }
    }




    private boolean isEditTextValid(EditText editText) {
        // Check if the EditText is not empty
        if (!TextUtils.isEmpty(editText.getText())) {
            // Check if the text contains only alphabets
            String inputText = editText.getText().toString().trim();
            if (inputText.matches("[a-zA-Z]+")) {
                // The input is valid (not empty and contains only alphabets)
                return true;
            } else {
                // Display an error message or handle the case where the input has non-alphabetic characters
               // editText.setError("Enter only alphabets");
                Toast.makeText(this, "Enter only alphabets", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Display an error message or handle the case where the input is empty
           // editText.setError("Field cannot be empty");
            Toast.makeText(this, "Field cannot be empty", Toast.LENGTH_SHORT).show();
        }

        // The input is not valid
        return false;
    }

    private boolean isEditTextNumeric(EditText editText) {
        // Check if the EditText is not empty
        if (!TextUtils.isEmpty(editText.getText())) {
            // Check if the text contains only numbers
            String inputText = editText.getText().toString().trim();
            if (inputText.matches("\\d+")) {
                // The input is valid (not empty and contains only numbers)
                return true;
            } else {
                // Display an error message or handle the case where the input has non-numeric characters
             //   editText.setError("Enter only numbers");
                Toast.makeText(this, "Enter only numbers", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Display an error message or handle the case where the input is empty
            //editText.setError("Field cannot be empty");
            Toast.makeText(this, "Field cannot be empty", Toast.LENGTH_SHORT).show();
        }

        // The input is not valid
        return false;
    }
}