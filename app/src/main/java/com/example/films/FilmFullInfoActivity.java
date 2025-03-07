package com.example.films;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FilmFullInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_full_info);

        Intent intent = getIntent();
        ((TextView)findViewById(R.id.film_full_title)).setText(intent.getStringExtra("title"));
        ((TextView)findViewById(R.id.film_full_description)).setText(intent.getStringExtra("description"));
        ((TextView)findViewById(R.id.film_full_genre)).setText(intent.getStringExtra("genre"));
        ((TextView)findViewById(R.id.film_full_year)).setText(Integer.toString(intent.getIntExtra("year",1980)));

        findViewById(R.id.bntBack).setOnClickListener(v -> finish());
    }
}
