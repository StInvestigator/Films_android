package com.example.films.model;

import android.annotation.SuppressLint;
import android.database.Cursor;

public class Film {
    private int id;
    private String title;
    private String genre;
    private String description;
    private int year;

    @SuppressLint("Range")
    public Film(Cursor cursor){
        this.id = cursor.getInt(cursor.getColumnIndex("id"));
        this.title = cursor.getString(cursor.getColumnIndex("title"));;
        this.genre = cursor.getString(cursor.getColumnIndex("genre"));;
        this.year = cursor.getInt(cursor.getColumnIndex("year"));;
        this.description = cursor.getString(cursor.getColumnIndex("description"));;
    }
    public Film(int id, String title, String genre, int year, String description) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public String getDescription(){
        return description;
    }
}
