package ru.naemys.movies.models;

import org.jetbrains.annotations.NotNull;

public class Movie {
    private String title;
    private String description;
    private int posterResource;
    private boolean favorite = false;

    public Movie() {
    }

    public Movie(String title, String description, int posterResource) {
        this.title = title;
        this.description = description;
        this.posterResource = posterResource;
    }

    public Movie(String title, String description, int posterResource, boolean favorite) {
        this.title = title;
        this.description = description;
        this.posterResource = posterResource;
        this.favorite = favorite;
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

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    @NotNull
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", posterResource=" + posterResource +
                '}';
    }
}
