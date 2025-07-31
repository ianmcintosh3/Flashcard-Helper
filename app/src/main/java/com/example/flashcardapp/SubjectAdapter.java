package com.example.flashcardapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder> {
    private ArrayList<String> subjects;
    private Context context;

    private static View.OnClickListener fOnItemClickListener;

    public SubjectAdapter(ArrayList<String> subjects, Context context) {
        this.subjects = subjects;
        this.context = context;
    }
    public void setfOnItemClickListener(View.OnClickListener listener) {
        fOnItemClickListener = listener;
    }
    @Override
    public SubjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.subject_list, parent, false);
        return new SubjectViewHolder(v);
    }
    @Override
    public void onBindViewHolder(SubjectViewHolder holder, int position) {
        holder.textViewSubject.setText(subjects.get(position));
    }
    @Override
    public int getItemCount() {
        return subjects.size();
    }
    public static class SubjectViewHolder extends RecyclerView.ViewHolder {
        TextView textViewSubject;
        public SubjectViewHolder(View itemView) {
            super(itemView);
            textViewSubject = itemView.findViewById(R.id.textViewSubject);
        }
    }
}
