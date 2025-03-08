package com.example.films;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import java.util.List;
import java.util.stream.Collectors;

public class AddGenreActivity extends AppCompatActivity {
    private EditText editName;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_genre);

        editName = findViewById(R.id.editName);
        btnSave = findViewById(R.id.btnSave);

        Log.d("FGH","here");
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);

        btnSave.setOnClickListener(v -> {
            String name = editName.getText().toString().trim();

            if (name.isEmpty()) {
                Toast.makeText(AddGenreActivity.this, "Fill all of the fields!", Toast.LENGTH_SHORT).show();
                return;
            }

            Genre genre = new Genre(0, name);

            if (id != -1) {
                Unit.getGenreRepository().updateGenre(genre, id);
            }
            else Unit.getGenreRepository().addGenre(genre);
            try {
                wait(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Toast.makeText(AddGenreActivity.this, "Success!", Toast.LENGTH_SHORT).show();
            finish();
        });

        if (id != -1) {
            Log.d("FGH","edit name");
            editName.setText(Unit.getGenreRepository().find(id).getName());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.list_films) {
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.add_film) {
            Intent intent = new Intent(getBaseContext(), AddFilmActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.list_genres) {
            Intent intent = new Intent(getBaseContext(), GenresListActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
