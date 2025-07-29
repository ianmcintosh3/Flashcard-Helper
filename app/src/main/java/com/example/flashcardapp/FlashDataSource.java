package com.example.flashcardapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

public class FlashDataSource {
    private SQLiteDatabase database;
    private FlashDBHelper dbHelper;

    public FlashDataSource(Context context) {
        dbHelper = new FlashDBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public boolean insertCardInfo(Flashcard f) {
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();
            initialValues.put("subject", f.getSubject());
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

            updateValues.put("subject", f.getSubject());
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
}
