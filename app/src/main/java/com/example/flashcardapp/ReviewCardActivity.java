package com.example.flashcardapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReviewCardActivity extends AppCompatActivity {
    ArrayList<Flashcard> flashcardArrayList;

    private FlashAdapter flashAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subject_list);

        FlashDataSource ds = new FlashDataSource(this);
        ds.open();
        flashcardArrayList = ds.getAllSubjects();
        ds.close();

        RecyclerView recyclerView = findViewById(R.id.recyclerViewSubjects);
        recyclerView.setAdapter(flashAdapter);

    }

    private View.OnClickListener onSubjectClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
            int position = viewHolder.getAdapterPosition();
        }
    };

}
