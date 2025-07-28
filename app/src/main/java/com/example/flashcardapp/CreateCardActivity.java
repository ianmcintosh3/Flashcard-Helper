package com.example.flashcardapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CreateCardActivity extends AppCompatActivity {

    private Flashcard currentFlashcard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.create_card);

        Intent intent = getIntent();
        int flashID = intent.getIntExtra("memoID", -1);
        String subject = intent.getStringExtra("subject");
        String front = intent.getStringExtra("front");
        String back = intent.getStringExtra("back");

        EditText subjectEditText = findViewById(R.id.editTextSubject);
        EditText frontEditText = findViewById(R.id.editTextFront);
        EditText backEditText = findViewById(R.id.editTextBack);

        subjectEditText.setText(subject);
        frontEditText.setText(front);
        backEditText.setText(back);

        if (flashID != -1){
            currentFlashcard = new Flashcard();
            currentFlashcard.setFlashcardID(flashID);
            currentFlashcard.setSubject(subject);
            currentFlashcard.setFront(front);
            currentFlashcard.setBack(back);

            subjectEditText.setText(subject);
            frontEditText.setText(front);
            backEditText.setText(back);
        } else{
            currentFlashcard = new Flashcard();
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.createCard), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void initSaveButton(){
        Button saveButton = findViewById(R.id.buttonSave);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                EditText editSubject = findViewById(R.id.editTextSubject);
                EditText editFront = findViewById(R.id.editTextFront);
                EditText editBack = findViewById(R.id.editTextBack);
            currentFlashcard.setSubject(editSubject.getText().toString());
            currentFlashcard.setFront(editFront.getText().toString());
            currentFlashcard.setBack(editBack.getText().toString());
            //boolean wasSuccessful;
                //come back and figure out how to create this and check
            }
        });
    }

}
