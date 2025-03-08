package com.example.films.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.films.AddFilmActivity;
import com.example.films.MainActivity;
import com.example.films.model.Film;
import com.example.films.FilmApplication;
import com.example.films.R;
import com.example.films.util.Unit;

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

        view.findViewById(R.id.btnUpdate).setOnClickListener(v->{
            Intent intent = new Intent(parent.getContext(), AddFilmActivity.class);
            intent.putExtra("id",film.getId());
            parent.getContext().startActivity(intent);
        });

        view.findViewById(R.id.btnDelete).setOnClickListener(v->{
            Unit.getFilmRepository().deleteFilm(film.getId());
            remove(film);
            notifyDataSetChanged();
        });

        return view;
    }
}
