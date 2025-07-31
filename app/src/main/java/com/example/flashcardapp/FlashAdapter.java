package com.example.flashcardapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FlashAdapter extends RecyclerView.Adapter<FlashAdapter.FlashViewHolder>{
    private ArrayList<Flashcard> flashcardArrayList;

    private static View.OnClickListener fOnItemClickListener;

    private final Context context;

    public FlashAdapter(ArrayList<Flashcard> flashcardArrayList, Context context) {
        this.flashcardArrayList = flashcardArrayList;
        this.context = context;
    }
    public void setfOnItemClickListener(View.OnClickListener listener) {
        fOnItemClickListener = listener;
    }

    public static class FlashViewHolder extends RecyclerView.ViewHolder {
        public TextView textSubject;

        public FlashViewHolder(View itemView){
            super(itemView);
            textSubject = itemView.findViewById(R.id.textViewSubject);
            itemView.setTag(this);
            itemView.setOnClickListener(fOnItemClickListener);
        }
    }

    public FlashViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.subject_list, parent, false);
        return new FlashViewHolder(v);
    }


    @Override
    public int getItemCount() {
        return 0;
    }
    @Override
    public void onBindViewHolder(@NonNull FlashViewHolder holder, int position) {
        Flashcard currentFlashcard = flashcardArrayList.get(position);
        holder.textSubject.setText(currentFlashcard.getSubject());
    }
}
