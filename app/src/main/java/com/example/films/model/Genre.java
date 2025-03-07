package com.example.films.model;

import android.annotation.SuppressLint;
import android.database.Cursor;

public class Genre {
    private int id;
    private String name;

    @SuppressLint("Range")
    public Genre(Cursor cursor) {
        this.id = cursor.getInt(cursor.getColumnIndex("id"));
        this.name = cursor.getString(cursor.getColumnIndex("name"));
    }

    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
