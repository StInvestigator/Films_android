package com.example.films.domain.repository;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.films.adapter.FilmArrayAdapter;
import com.example.films.domain.FilmSQLHelper;
import com.example.films.model.Film;
import com.j256.ormlite.dao.BaseDaoImpl;

import java.sql.SQLException;

public class FilmRepository extends BaseDaoImpl<Film, Integer> {

    public FilmRepository(FilmSQLHelper sqlHelper) throws SQLException {
        super(sqlHelper.getConnectionSource(), Film.class);
    }

    public Film find(Integer id) throws SQLException {
        return queryForId(id);
    }

    public FilmArrayAdapter getFilmArrayAdapter() throws SQLException {
        FilmArrayAdapter adapter = new FilmArrayAdapter();
        adapter.addAll(queryForAll());
        return adapter;
    }

    public void addFilm(Film film) {
        try {
            this.create(film);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateFilm(Film film, int id) {
        film.setId(id);
        try {
            this.update(film);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteFilm(int id) throws SQLException {
        deleteById(id);
    }
}
