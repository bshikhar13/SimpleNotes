package com.example.dexter.simplenote.DB;

/**
 * Created by Dexter on 3/20/2016.
 */
public class Note {
    int id;
    String note;
    String color;
    String dateTime;

    public Note(String note, String color, String dateTime){
        this.note = note;
        this.color = color;
        this.dateTime = dateTime;
    }


    public Note(String note, String dateTime){
        this.note = note;
        this.color = "white";
        this.dateTime = dateTime;
    }

    public Note(int id, String note, String color, String dateTime){
        this.id = id;
        this.note = note;
        this.color = "white";
        this.dateTime = dateTime;
    }

    public String getNote() {
        return note;
    }

    public String getColor() {
        return color;
    }

    public String getDateTime() {
        return dateTime;
    }

    public int getId() {
        return id;
    }
}


