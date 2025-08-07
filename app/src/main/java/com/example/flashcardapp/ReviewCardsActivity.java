package com.example.flashcardapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ReviewCardsActivity extends AppCompatActivity {
    private Flashcard currentFlashcard;
    FlashAdapter flashAdapter;
    ArrayList<Flashcard> flashcardArrayList;
    private int currentIndex = 0;
    private boolean showingFront = true;
    private TextView subjectTextView,frontCardTextView, backCardTextView;
    private Button nextButton;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.review_cards);
        subjectTextView = findViewById(R.id.textViewSubjectOfCards);
        frontCardTextView = findViewById(R.id.textViewFrontCard);
        backCardTextView = findViewById(R.id.textViewBackCard);
        nextButton = findViewById(R.id.buttonNext);

        String subject = getIntent().getStringExtra("subject");
        subjectTextView.setText(subject);


        FlashDataSource ds = new FlashDataSource(this);
        ds.open();
        flashcardArrayList = ds.getFlashcardsBySubject(subject);
        ds.close();
        flashAdapter = new FlashAdapter(flashcardArrayList,this);
        backCardTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showingFront = !showingFront;
                cardDisplay();
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex + 1) % flashcardArrayList.size();
                showingFront = true;
                cardDisplay();
            }
        });

    }

    private void cardDisplay(){
        if(flashcardArrayList.isEmpty()) {
            frontCardTextView.setText("No Cards");
            backCardTextView.setText("");
            nextButton.setEnabled(false);
            return;
        }
        Flashcard card = flashcardArrayList.get(currentIndex);
        if(showingFront){
            frontCardTextView.setText(card.getFront());
            backCardTextView.setText("Show Front");
        } else{
            frontCardTextView.setText(card.getBack());
            backCardTextView.setText("Show Front");
        }
        nextButton.setEnabled(flashcardArrayList.size() > 1);

    }

}
