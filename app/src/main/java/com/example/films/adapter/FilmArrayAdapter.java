package com.example.films.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.films.model.Film;
import com.example.films.FilmApplication;
import com.example.films.R;

public class FilmArrayAdapter extends ArrayAdapter<Film> {
    public FilmArrayAdapter() {
        super(FilmApplication.getApplication(), R.layout.film_layout, R.id.film_title);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = super.getView(position, convertView, parent);

        Film film = getItem(position);
        ((TextView)view.findViewById(R.id.film_title)).setText(film.getTitle());
        ((TextView)view.findViewById(R.id.film_genre)).setText(film.getGenre());
        ((TextView)view.findViewById(R.id.film_year)).setText(String.format("%d year", film.getYear()));

        return view;
    }
}
