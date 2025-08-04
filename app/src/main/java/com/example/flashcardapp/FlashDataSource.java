package com.example.flashcardapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
public class FlashDataSource {
    private SQLiteDatabase database;
    private FlashDBHelper dbHelper;

    public FlashDataSource(Context context) {
        dbHelper = new FlashDBHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public boolean insertCardInfo(Flashcard f) {
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();
            initialValues.put("subject", normalizeSubject(f.getSubject()));
            initialValues.put("front", f.getFront());
            initialValues.put("back", f.getBack());
            long id = database.insert("flash", null, initialValues);
            didSucceed = (id != -1);
        }
        catch(Exception e) {
            //d
        }
        return didSucceed;
    }

    public boolean updateCardInfo(Flashcard f){
        boolean didSucceed = false;
        try {
            Long rowId = (long) f.getFlashcardID();
            ContentValues updateValues = new ContentValues();

            updateValues.put("subject", normalizeSubject(f.getSubject()));
            updateValues.put("front", f.getFront());
            updateValues.put("back", f.getBack());

            didSucceed = database.update("flash", updateValues, "_id=" + rowId, null) > 0;

        }
        catch (Exception e){
            //
        }
        return didSucceed;
    }
    public int getLastCardID() {
        int lastId;
        try {
            String query = "SELECT MAX(_id) FROM flash";
            Cursor cursor = database.rawQuery(query, null);

            cursor.moveToFirst();
            lastId = cursor.getInt(0);
            cursor.close();
        } catch (Exception e) {
            lastId = -1;
        }
        return lastId;
    }
    public ArrayList<Flashcard> getAllFlashcards() {
        ArrayList<Flashcard> flashcards = new ArrayList<>();
        try {
            Cursor cursor = database.rawQuery("SELECT _id, subject, front, back FROM flash", null);
            if (cursor.moveToFirst()) {
                do {
                    Flashcard card = new Flashcard();
                    card.setFlashcardID(cursor.getInt(0));
                    card.setSubject(cursor.getString(1));
                    card.setFront(cursor.getString(2));
                    card.setBack(cursor.getString(3));
                    flashcards.add(card);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e) {
            Log.e("FlashDataSource", "Error getting flashcards", e);
        }
        return flashcards;
    }
    public ArrayList<String> getAllSubjects(){
        ArrayList<String> subjects = new ArrayList<>();
        try {
            Cursor cursor = database.rawQuery("SELECT MIN(subject) as subject FROM flash GROUP BY LOWER(subject)", null);
            if(cursor.moveToFirst()){
                do{
                    String subject = cursor.getString(0);
                    subjects.add(subject);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e) {
            //
        }
        return subjects;

    }
    public static String normalizeSubject(String subject) {
        if (subject == null || subject.isEmpty()) return subject;
        subject = subject.trim();
        return subject.substring(0, 1).toUpperCase() + subject.substring(1).toLowerCase();
    }
    public ArrayList<Flashcard> getFlashcardsBySubject(String subject) {
        subject = normalizeSubject(subject);
        ArrayList<Flashcard> flashcards = new ArrayList<>();
        try {
            Cursor cursor = database.rawQuery("SELECT _id, subject, front, back FROM flash WHERE subject = ?", new String[]{subject});
            if (cursor.moveToFirst()) {
                do {
                    Flashcard card = new Flashcard();
                    card.setFlashcardID(cursor.getInt(0));
                    card.setSubject(cursor.getString(1));
                    card.setFront(cursor.getString(2));
                    card.setBack(cursor.getString(3));
                    flashcards.add(card);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e) {
            Log.e("FlashDataSource", "Error getting flashcards", e);
        }
        return flashcards;
    }
}
