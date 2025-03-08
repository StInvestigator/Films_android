package com.example.films;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.films.model.Film;
import com.example.films.util.Unit;

public class GenresListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genres_list);

        ListView list = findViewById(R.id.genre_list);
        list.setAdapter(Unit.getGenreRepository().getGenreArrayAdapter());
    }

    @Override
    protected void onResume() {
        ListView list = findViewById(R.id.genre_list);
        list.setAdapter(Unit.getGenreRepository().getGenreArrayAdapter());
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId()==R.id.add_film){
            Intent intent = new Intent(getBaseContext(), AddFilmActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.add_genre){
            Intent intent = new Intent(getBaseContext(), AddGenreActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.list_films){
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}