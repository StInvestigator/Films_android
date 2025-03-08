package com.example.films.adapter;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.films.AddFilmActivity;
import com.example.films.AddGenreActivity;
import com.example.films.FilmApplication;
import com.example.films.R;
import com.example.films.model.Film;
import com.example.films.model.Genre;
import com.example.films.util.Unit;

public class GenreArrayAdapter extends ArrayAdapter<Genre> {
    public GenreArrayAdapter() {
        super(FilmApplication.getApplication(), R.layout.genre_layout, R.id.genre_name);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = super.getView(position, convertView, parent);

        Genre genre = getItem(position);
        ((TextView)view.findViewById(R.id.genre_name)).setText(genre.getName());

        view.findViewById(R.id.btnUpdate).setOnClickListener(v->{
            Intent intent = new Intent(parent.getContext(), AddGenreActivity.class);
            intent.putExtra("id",genre.getId());
            parent.getContext().startActivity(intent);
        });

        view.findViewById(R.id.btnDelete).setOnClickListener(v->{
            Unit.getGenreRepository().deleteGenre(genre.getId());
            remove(genre);
            notifyDataSetChanged();
        });

        return view;
    }
}
