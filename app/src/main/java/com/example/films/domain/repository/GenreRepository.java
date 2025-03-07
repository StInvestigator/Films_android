package com.example.films.domain.repository;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.films.domain.FilmSQLHelper;
import com.example.films.model.Film;
import com.example.films.model.Genre;

import java.util.ArrayList;
import java.util.List;

public class GenreRepository {
    FilmSQLHelper sqlHelper;
    SQLiteDatabase database;
    Cursor cursor;

    public GenreRepository(FilmSQLHelper sqlHelper) {
        this.sqlHelper = sqlHelper;
        database = sqlHelper.getReadableDatabase();
        cursor = database.rawQuery("select id, name from genres", new String[]{});
    }

    public Genre getItem(int pos) {
        cursor.moveToPosition(pos);
        return new Genre(cursor);
    }

    public List<Genre> findAll() {
        List<Genre> genres = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            genres.add(new Genre(cursor));
            cursor.moveToNext();
        }
        return genres;
    }

    public void addGenre(Genre genre) {
        database.execSQL("insert into genres(name) values" +
                "(?)", new String[]{genre.getName()});
    }
}
