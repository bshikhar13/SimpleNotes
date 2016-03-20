package com.example.dexter.simplenote.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dexter on 3/20/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "SimpleNoteDB";

    //Table Name
    private static final String TABLE_NOTE = "InfoCard";

    private static final String KEY_ID = "id";
    private static final String KEY_NOTE = "note";
    private static final String KEY_COLOR = "color";
    private static final String KEY_DATETIME = "datetime";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String CREATE_NEWCUSTOMER = "create table newcustomer (_id integer autoincrement, cname text, date text, caddress text, PRIMARY KEY(_id, cname));";
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_INFOCARD_TABLE = "CREATE TABLE " + TABLE_NOTE + " ("
                + KEY_ID + " integer autoincrement , " + KEY_NOTE + " TEXT , "
                + KEY_COLOR + " TEXT , " + KEY_DATETIME + " TEXT , PRIMARY KEY ( " + KEY_ID + " ) ) ;";

        db.execSQL(CREATE_INFOCARD_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);
        onCreate(db);
    }

    public long addNote(Note note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NOTE, note.getNote());
        values.put(KEY_COLOR, note.getColor());
        values.put(KEY_DATETIME, note.getDateTime());
        long res = db.insert(TABLE_NOTE, null, values);
        db.close();
        return res;
    }

    public List<Note> getNotes(){
        List<Note> NoteList = new ArrayList<Note>() ;
        String selectQuery = "SELECT * FROM " + TABLE_NOTE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Note note = new Note(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)) ;
                // Adding contact to list
                NoteList.add(note);
            } while (cursor.moveToNext());
        }
        return NoteList;
    }

    public int getCountNoteCards(){
        String countQuery = "SELECT * FROM " + TABLE_NOTE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

    public int deleteNote(Note note) {
        int id = note.getId();
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NOTE, KEY_ID + " = ? ",
                new String[]{Integer.toString(id)});
    }

    public int changeColorNote(Note note, String color){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_COLOR, color);

        return db.update(TABLE_NOTE, values, KEY_ID + " = ? ",
                new String[]{String.valueOf(note.getId())});
    }

}
