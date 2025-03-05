package com.example.films;

import android.app.Application;

public class FilmApplication extends Application {
    private static Application _app;
    public static Application getApplication(){
        return _app;
    }

    public FilmApplication(){
        _app = this;
    }
}
