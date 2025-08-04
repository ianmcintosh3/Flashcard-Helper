package com.example.flashcardapp;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
        FlashDataSource ds = new FlashDataSource(this);
        ds.open();
        flashcardArrayList = ds.getAllFlashcards();
        ds.close();

        flashAdapter = new FlashAdapter(flashcardArrayList,this);
        flashAdapter.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListOfCardsActivity.this, MainActivity.class);
                intent.setFlags(FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(flashAdapter);

    }
}
