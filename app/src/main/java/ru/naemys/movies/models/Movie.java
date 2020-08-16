package ru.naemys.movies.models;

public class Movie {
    private String title;
    private String description;
    private int posterResource;

    public Movie() {
    }

    public Movie(String title, String description, int posterResource) {
        this.title = title;
        this.description = description;
        this.posterResource = posterResource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPosterResource() {
        return posterResource;
    }

    public void setPosterResource(int posterResource) {
        this.posterResource = posterResource;
    }
}
