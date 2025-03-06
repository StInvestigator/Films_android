package com.example.films;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FilmStorage {
    private static final String FILE_NAME = "films.json";

    public static void saveFilms(Context context, List<Film> films) {
        Gson gson = new Gson();
        String json = gson.toJson(films);

        File file = new File(context.getFilesDir(), FILE_NAME);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            Log.e("FilmStorage", "Error saving films", e);
        }
    }

    public static List<Film> loadFilms(Context context) {
        File file = new File(context.getFilesDir(), FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (FileReader reader = new FileReader(file)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, new TypeToken<List<Film>>() {}.getType());
        } catch (IOException e) {
            Log.e("FilmStorage", "Error loading films", e);
            return new ArrayList<>();
        }
    }
}
