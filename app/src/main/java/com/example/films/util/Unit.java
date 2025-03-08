package com.example.films.util;

import android.util.Log;

import com.example.films.domain.FilmSQLHelper;
import com.example.films.domain.repository.FilmRepository;
import com.example.films.domain.repository.GenreRepository;

import java.sql.SQLException;

public class Unit {
    private FilmSQLHelper filmSQLHelper = new FilmSQLHelper();
    private FilmRepository filmRepository;
    private GenreRepository genreRepository;

    private static Unit instance;

    static{
        try {
            instance = new Unit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Unit() throws SQLException {
        filmRepository = new FilmRepository(filmSQLHelper);
        genreRepository = new GenreRepository(filmSQLHelper);
    }

    public static FilmRepository getFilmRepository() {
        return instance.filmRepository;
    }

    public static GenreRepository getGenreRepository() {
        return instance.genreRepository;
    }
}
