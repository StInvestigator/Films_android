package com.example.films;

import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Films {
    private static final List<Film> films = new ArrayList<>();

    public static void addFilm(Film film) {
        films.add(film);
    }

    public static List<Film> getFilms() {
        return films;
    }

    public static FilmArrayAdapter getAdapter() {
        FilmArrayAdapter adapter = new FilmArrayAdapter();
        films.forEach(adapter::add);
        return adapter;
    }

    private Films() {
    }
}
