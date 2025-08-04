package com.example.flashcardapp;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClickSubjectActivity extends AppCompatActivity {
    ArrayList<Flashcard> flashcardArrayList;
    ArrayList<String> subjectArrayList;
    private FlashAdapter flashAdapter;
    private SubjectAdapter subjectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_subject);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewSubjects);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FlashDataSource ds = new FlashDataSource(this);
        ds.open();
        subjectArrayList = ds.getAllSubjects();
        ds.close();

        subjectAdapter = new SubjectAdapter(subjectArrayList, this);
        subjectAdapter.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = recyclerView.getChildAdapterPosition(view);
                String selectedSubject = subjectArrayList.get(position);

                Intent intent = new Intent(ClickSubjectActivity.this, ListOfCardsActivity.class);
                intent.setFlags(FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("subject", selectedSubject); // Optional: pass selected subject
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(subjectAdapter);

    }


}


