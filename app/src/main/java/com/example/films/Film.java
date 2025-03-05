package com.example.films;

public class Film {
    private int id;
    private String title;
    private String genre;
    private int year;

    public Film(int id, String title, String genre, int year) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }
}
