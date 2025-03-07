package com.example.films.util;

import android.util.Log;

import com.example.films.domain.FilmSQLHelper;
import com.example.films.domain.repository.FilmRepository;
import com.example.films.domain.repository.GenreRepository;

public class Unit {
    private FilmSQLHelper filmSQLHelper = new FilmSQLHelper();
    private FilmRepository filmRepository;
    private GenreRepository genreRepository;

    private static Unit instance;

    static{
        instance = new Unit();
    }

    private Unit(){
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
