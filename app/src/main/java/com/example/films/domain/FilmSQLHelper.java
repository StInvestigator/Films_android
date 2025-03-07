package com.example.films.domain;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.films.FilmApplication;

public class FilmSQLHelper extends SQLiteOpenHelper {
    public FilmSQLHelper(){
        super(FilmApplication.getApplication(),"FilmDatabase",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table genres(id integer primary key autoincrement, name text not null)");
        db.execSQL("create table films(id integer primary key autoincrement, title text not null, description text," +
                "year integer, genre_id integer, foreign key (genre_id) references genres (id))");

        db.execSQL("insert into genres(name) values ('Drama'),('Comedy'),('Action'),('Fiction'),('Horror')");
        db.execSQL("insert into films(title, description, year, genre_id) values" +
                "('super film 1','This is just your regular ultra interesting film', 2005, 1)," +
                "('super film 2','This is just your regular ultra interesting film`s sequel', 2006, 1)," +
                "('super film 3','This is just your regular ultra interesting film`s triquel', 2007, 4)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
