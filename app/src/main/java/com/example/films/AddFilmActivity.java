package com.example.films;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.films.model.Film;
import com.example.films.model.Genre;
import com.example.films.util.Unit;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AddFilmActivity extends AppCompatActivity {
    private EditText editTitle, editYear, editDesc;
    private Spinner spinnerGenre;
    private Button btnSave;
    private String selectedGenre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_film);

        editTitle = findViewById(R.id.editTitle);
        editYear = findViewById(R.id.editYear);
        editDesc = findViewById(R.id.editDesc);
        spinnerGenre = findViewById(R.id.spinnerGenre);
        btnSave = findViewById(R.id.btnSave);

        List<String> genres = Unit.getGenreRepository().findAll().stream().map(Genre::getName).collect(Collectors.toList());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, genres);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGenre.setAdapter(adapter);

        spinnerGenre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedGenre = genres.get(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedGenre = "";
            }
        });

        btnSave.setOnClickListener(v -> {
            String title = editTitle.getText().toString().trim();
            String yearStr = editYear.getText().toString().trim();
            String desc = editDesc.getText().toString().trim();

            if (title.isEmpty() || yearStr.isEmpty() || selectedGenre.isEmpty() || desc.isEmpty()) {
                Toast.makeText(AddFilmActivity.this, "Fill all of the fields!", Toast.LENGTH_SHORT).show();
                return;
            }

            int year = Integer.parseInt(yearStr);
            Film newFilm = new Film(0, title, selectedGenre, year, desc);

            Unit.getFilmRepository().addFilm(newFilm);
            try {
                wait(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Toast.makeText(AddFilmActivity.this, "Film added: " + newFilm.getTitle(), Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId()==R.id.list_films){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
