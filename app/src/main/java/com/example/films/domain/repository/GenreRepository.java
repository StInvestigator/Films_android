package com.example.films.domain.repository;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.films.adapter.FilmArrayAdapter;
import com.example.films.adapter.GenreArrayAdapter;
import com.example.films.domain.FilmSQLHelper;
import com.example.films.model.Film;
import com.example.films.model.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GenreRepository {
    FilmSQLHelper sqlHelper;
    SQLiteDatabase database;
    Cursor cursor;

    public GenreRepository(FilmSQLHelper sqlHelper) {
        this.sqlHelper = sqlHelper;
        database = sqlHelper.getReadableDatabase();
    }
    @SuppressLint("Range")
    public Genre find(int id) {
        cursor = database.rawQuery("select id, name from genres", new String[]{});
        cursor.moveToFirst();
        Log.d("FGH","find start");
        while (cursor.getInt(cursor.getColumnIndex("id")) != id) {
            cursor.moveToNext();
        }
        Log.d("FGH","find end");
        return new Genre(cursor);
    }

    @SuppressLint("Range")
    public int getPosition(String name) {
        cursor = database.rawQuery("select id, name from genres", new String[]{});
        cursor.moveToFirst();
        while (!Objects.equals(cursor.getString(cursor.getColumnIndex("name")), name)) {
            cursor.moveToNext();
        }
        return cursor.getPosition();
    }

    public List<Genre> findAll() {
        cursor = database.rawQuery("select id, name from genres", new String[]{});
        List<Genre> genres = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            genres.add(new Genre(cursor));
            cursor.moveToNext();
        }
        return genres;
    }

    public GenreArrayAdapter getGenreArrayAdapter() {
        cursor = database.rawQuery("select id, name from genres", new String[]{});
        GenreArrayAdapter adapter = new GenreArrayAdapter();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            adapter.add(new Genre(cursor));
            cursor.moveToNext();
        }
        return adapter;
    }

    public void addGenre(Genre genre) {
        database.execSQL("insert into genres(name) values" +
                "(?)", new String[]{genre.getName()});
    }

    public void updateGenre(Genre genre, int id) {
        database.execSQL("update genres set name = ? " +
                "where id = ?", new String[]{genre.getName(), Integer.toString(id)});
    }

    public void deleteGenre(int id) {
        database.execSQL("delete from genres where id = ?", new String[]{Integer.toString(id)});
    }
}
