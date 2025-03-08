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

import java.sql.SQLException;

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

        try {
            list.setAdapter(Unit.getFilmRepository().getFilmArrayAdapter());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onResume() {
        ListView list = findViewById(R.id.film_list);
        try {
            list.setAdapter(Unit.getFilmRepository().getFilmArrayAdapter());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        else if(item.getItemId()==R.id.list_genres){
            Intent intent = new Intent(getBaseContext(), GenresListActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}