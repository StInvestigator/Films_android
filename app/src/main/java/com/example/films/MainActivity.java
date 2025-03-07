package com.example.films;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView list = findViewById(R.id.film_list);
        list.setOnItemClickListener((parent,view,position,id)->{
            Film film = (Film) parent.getItemAtPosition(position);
            Intent intent = new Intent(getBaseContext(), FilmFullInfoActivity.class);
            intent.putExtra("title",film.getTitle());
            intent.putExtra("genre",film.getGenre());
            intent.putExtra("description",film.getDescription());
            intent.putExtra("year",film.getYear());
            startActivity(intent);
        });
        list.setAdapter(Unit.getFilmRepository().getFilmArrayAdapter());
    }

    @Override
    protected void onResume() {
        ListView list = findViewById(R.id.film_list);
        list.setAdapter(Unit.getFilmRepository().getFilmArrayAdapter());
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

        return super.onOptionsItemSelected(item);
    }
}