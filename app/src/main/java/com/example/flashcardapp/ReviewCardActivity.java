package com.example.flashcardapp;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReviewCardActivity extends AppCompatActivity {
    ArrayList<Flashcard> flashcardArrayList;

    private FlashAdapter flashAdapter;

    private View.OnClickListener onSubjectClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
            int position = viewHolder.getAdapterPosition();
        }
    };

}
