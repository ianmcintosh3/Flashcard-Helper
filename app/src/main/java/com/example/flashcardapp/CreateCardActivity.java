package com.example.flashcardapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        int flashID = intent.getIntExtra("flashcardID", -1);
        String subject = intent.getStringExtra("subject");
        String front = intent.getStringExtra("front");
        String back = intent.getStringExtra("back");

        EditText subjectEditText = findViewById(R.id.editTextSubject);
        EditText frontEditText = findViewById(R.id.editTextFront);
        EditText backEditText = findViewById(R.id.editTextBack);

        subjectEditText.setText(subject);
        frontEditText.setText(front);
        backEditText.setText(back);

        if (flashID != -1) {
            currentFlashcard = new Flashcard();
            currentFlashcard.setFlashcardID(flashID);
            currentFlashcard.setSubject(subject);
            currentFlashcard.setFront(front);
            currentFlashcard.setBack(back);

            subjectEditText.setText(subject);
            frontEditText.setText(front);
            backEditText.setText(back);
        } else {
            currentFlashcard = new Flashcard();
        }
        initSaveButton();
        initDoneButton();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.createCard), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setForEditing(true);
    }

    private void initSaveButton() {
        Button saveButton = findViewById(R.id.buttonSave);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                EditText editSubject = findViewById(R.id.editTextSubject);
                EditText editFront = findViewById(R.id.editTextFront);
                EditText editBack = findViewById(R.id.editTextBack);
                currentFlashcard.setSubject(editSubject.getText().toString());
                currentFlashcard.setFront(editFront.getText().toString());
                currentFlashcard.setBack(editBack.getText().toString());
                boolean wasSuccessful;
                FlashDataSource ds = new FlashDataSource(CreateCardActivity.this);
                try {
                    ds.open();
                    if (currentFlashcard.getFlashcardID() == -1) {
                        wasSuccessful = ds.insertCardInfo(currentFlashcard);

                        if (wasSuccessful) {
                            int newId = ds.getLastCardID();
                            currentFlashcard.setFlashcardID(newId);
                        }
                    } else {
                        wasSuccessful = ds.updateCardInfo(currentFlashcard);
                    }
                    ds.close();
                } catch (Exception e) {
                    wasSuccessful = false;
                }
                if (wasSuccessful) {
                    Toast.makeText(CreateCardActivity.this, "Flashcard saved successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CreateCardActivity.this, "Failed to save Flashcard", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void initDoneButton() {
        Button doneButton = findViewById(R.id.buttonDone);
        doneButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(CreateCardActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
    public void setForEditing(boolean enabled){
        EditText editSubject = findViewById(R.id.editTextSubject);
        EditText editFront = findViewById(R.id.editTextFront);
        EditText editBack = findViewById(R.id.editTextBack);
        Button buttonSave = findViewById(R.id.buttonSave);
        editSubject.setEnabled(enabled);
        editFront.setEnabled(enabled);
        editBack.setEnabled(enabled);
        buttonSave.setEnabled(enabled);



    }

}

