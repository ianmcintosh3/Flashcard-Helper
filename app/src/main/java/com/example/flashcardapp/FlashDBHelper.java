package com.example.flashcardapp;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FlashDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Flash.db";

    private static final int DATABASE_VERSION = 2;

    private static final String CREATE_TABLE_FLASH =
            "create table flash (_id integer primary key autoincrement,"
                    + "subject text not null, front text,"
                    + "back text);";

    public FlashDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);}
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE_FLASH);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        Log.w(FlashDBHelper.class.getName(),
                "Upgrading database from version" + oldVersion + "to"
        + newVersion + ", which will destroy old data");
        db.execSQL("DROP TABLE IF EXISTS flash");
        onCreate(db);
    }

}
