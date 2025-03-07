package com.example.films.domain.repository;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.films.adapter.FilmArrayAdapter;
import com.example.films.domain.FilmSQLHelper;
import com.example.films.model.Film;

import java.util.ArrayList;
import java.util.List;

public class FilmRepository {
    FilmSQLHelper sqlHelper;
    SQLiteDatabase database;
    Cursor cursor;

    public FilmRepository(FilmSQLHelper sqlHelper) {
        this.sqlHelper = sqlHelper;
        database = sqlHelper.getReadableDatabase();
        cursor = database.rawQuery("select f.id as id, f.title as title, f.year as year, f.description as description," +
                "g.name as genre " +
                "from films as f inner join genres as g on f.genre_id = g.id", new String[]{});
    }

    public Film getItem(int pos) {
        cursor.moveToPosition(pos);
        return new Film(cursor);
    }

    public List<Film> findAll() {
        List<Film> films = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            films.add(new Film(cursor));
            cursor.moveToNext();
        }
        return films;
    }

    public FilmArrayAdapter getFilmArrayAdapter() {
        FilmArrayAdapter adapter = new FilmArrayAdapter();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            adapter.add(new Film(cursor));
            cursor.moveToNext();
        }

        return adapter;
    }

    public void addFilm(Film film) {
        database.execSQL("insert into films(title, description, year, genre_id) values" +
                "(?, ?, ?, (select id from genres where name = ?))", new String[]{film.getTitle(), film.getDescription(),
                Integer.toString(film.getYear()), film.getGenre()});
    }
}
