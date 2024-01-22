package com.example.bibleapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class FavouriteVersesListActivity extends AppCompatActivity implements FavouriteRecyclerAdapter.FavouriteClickListener {
    ArrayList<BibleItem> FavouriteData;
    FavouriteRecyclerAdapter adapter;
    RecyclerView FavouriteRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_verses_list);

        FavouriteData =  ((MyApp)getApplication()).appBibleList; // data

        FavouriteRecyclerView = findViewById(R.id.recyclerlist);
        adapter = new FavouriteRecyclerAdapter(FavouriteData,this);
        adapter.listener = this; // step 4
        FavouriteRecyclerView.setAdapter(adapter);
        // todoRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        FavouriteRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onFavouriteClicked(int i) {
        Toast.makeText(this,FavouriteData.get(i).getBookName(),Toast.LENGTH_LONG).show();
        String VerseDeatail =FavouriteData.get(i).verseText+"\n"+FavouriteData.get(i).bookName+" "+FavouriteData.get(i).chapterNumber+" "+FavouriteData.get(i).verseNumber+"\n"+" Was saved at "+FavouriteData.get(i).getTimeStap();


        Intent detailFavIntent = new Intent(FavouriteVersesListActivity.this, DetailedFavVerseActivity.class);
        detailFavIntent.putExtra("detail",VerseDeatail);
        // parent.getChildAt(i).setBackgroundColor(getColor(R.color.red));
        startActivity(detailFavIntent);
    }
}