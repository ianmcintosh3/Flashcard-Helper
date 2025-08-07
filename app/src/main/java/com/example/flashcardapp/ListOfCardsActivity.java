package com.example.flashcardapp;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ListOfCardsActivity extends AppCompatActivity {
    private FlashAdapter flashAdapter;
    ArrayList<Flashcard> flashcardArrayList;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        String subject = getIntent().getStringExtra("subject");
        setContentView(R.layout.select_flashcard);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewFlashcards);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FlashDataSource ds = new FlashDataSource(this);
        ds.open();
        flashcardArrayList = ds.getFlashcardsBySubject(subject);
        ds.close();

        flashAdapter = new FlashAdapter(flashcardArrayList,this);
        flashAdapter.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
                int position = viewHolder.getAdapterPosition();

                if (position != RecyclerView.NO_POSITION) { // Ensure valid position
                    Flashcard selectedFlashcard = flashcardArrayList.get(position);
                    String flashSubject = selectedFlashcard.getSubject();
                    String flashFront = selectedFlashcard.getFront();
                    String flashBack = selectedFlashcard.getBack();
                    int FlashId = selectedFlashcard.getFlashcardID();
                    Intent intent = new Intent(ListOfCardsActivity.this, CreateCardActivity.class);
                    intent.putExtra("subject", flashSubject);
                    intent.putExtra("front", flashFront);
                    intent.putExtra("back", flashBack);
                    intent.setFlags(FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }});
        recyclerView.setAdapter(flashAdapter);

    }
}
