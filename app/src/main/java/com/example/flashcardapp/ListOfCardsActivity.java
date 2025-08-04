package com.example.flashcardapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListOfCardsActivity extends AppCompatActivity {
    private FlashAdapter flashAdapter;
    ArrayList<Flashcard> flashcardArrayList;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_flashcard);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewFlashcards);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
