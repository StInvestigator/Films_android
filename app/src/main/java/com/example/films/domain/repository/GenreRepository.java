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
import com.example.films.util.Unit;
import com.j256.ormlite.dao.BaseDaoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GenreRepository extends BaseDaoImpl<Genre, Integer> {
    FilmSQLHelper sqlHelper;
    SQLiteDatabase database;
    Cursor cursor;

    public GenreRepository(FilmSQLHelper sqlHelper) throws SQLException {
        super(sqlHelper.getConnectionSource(), Genre.class);
        this.sqlHelper = sqlHelper;
        database = sqlHelper.getReadableDatabase();
    }

    public Genre find(int id) {
        try {
            return queryForId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Genre find(String name) {
        try {
            return queryForEq("name", name).get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressLint("Range")
    public int getPosition(String name) {
        Log.d("FGH","here");
        cursor = database.rawQuery("select id, name from genre", new String[]{});
        cursor.moveToFirst();
        while (!Objects.equals(cursor.getString(cursor.getColumnIndex("name")), name)) {
            cursor.moveToNext();
        }
        return cursor.getPosition();
    }

    public List<Genre> findAll() {
        try {
            return queryForAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public GenreArrayAdapter getGenreArrayAdapter() {
        GenreArrayAdapter adapter = new GenreArrayAdapter();
        try {
            adapter.addAll(queryForAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return adapter;
    }

    public void addGenre(Genre genre) {
        try {
            create(genre);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateGenre(Genre genre, int id) {
        genre.setId(id);
        try {
            update(genre);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteGenre(int id) {
        try {
            List<Film> films = Unit.getFilmRepository().queryForEq("genre_id", id);
            for (Film film : films) {
                Unit.getFilmRepository().delete(film);
            }
            deleteById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
