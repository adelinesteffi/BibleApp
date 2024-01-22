package com.example.bibleapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FavouriteRecyclerAdapter extends
        RecyclerView.Adapter<FavouriteRecyclerAdapter.FavouriteViewHolder>
{
    interface FavouriteClickListener{ // step 1
        void onFavouriteClicked(int i);

    }

    ArrayList<BibleItem> favourite_list;
    Context context;
    FavouriteClickListener listener;// step 2

    public FavouriteRecyclerAdapter(ArrayList<BibleItem> favourite_list, Context context) {
        this.favourite_list = favourite_list;
        this.context = context;
    }

    @NonNull
    @Override
    public FavouriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_view_items,parent,false);
        return  new FavouriteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteViewHolder holder, int position) {
        TextView book_item_selcted = holder.itemView.findViewById(R.id.BookTextView);
        TextView chapter_item_selcted = holder.itemView.findViewById(R.id.chapterTextView);
        TextView verse_item_selcted = holder.itemView.findViewById(R.id.verseTextView);
        book_item_selcted.setText(favourite_list.get(position).getBookName());

        chapter_item_selcted.setText(favourite_list.get(position).getChapterNumber());

        verse_item_selcted.setText(  favourite_list.get(position).getVerseNumber());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.onFavouriteClicked(holder.getAdapterPosition()); // step 5
            }
        });
    }

    @Override
    public int getItemCount() {
        return favourite_list.size();
    }

    class FavouriteViewHolder extends RecyclerView.ViewHolder {
        public FavouriteViewHolder(@NonNull View itemView) {
            super(itemView);
        }

    }
}
