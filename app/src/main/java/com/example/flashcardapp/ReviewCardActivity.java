package com.example.flashcardapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReviewCardActivity extends AppCompatActivity {
    ArrayList<Flashcard> flashcardArrayList;
    ArrayList<String> subjectArrayList;
    private FlashAdapter flashAdapter;
    private SubjectAdapter subjectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_subject);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewSubjects);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FlashDataSource ds = new FlashDataSource(this);
        ds.open();
        subjectArrayList = ds.getAllSubjects();
        ds.close();

        subjectAdapter = new SubjectAdapter(subjectArrayList, this);
        recyclerView.setAdapter(subjectAdapter);
        subjectAdapter.setfOnItemClickListener(onSubjectClickListener);

    }

    private View.OnClickListener onSubjectClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
            int position = viewHolder.getAdapterPosition();
        }
    };

}
