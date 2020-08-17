package ru.naemys.movies.models;

import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

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
    public boolean equals(@Nullable Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != Movie.class) return false;

        Movie movie = (Movie) obj;

        return movie.getPosterResource() == posterResource
                && Objects.equals(movie.getTitle(), title)
                && Objects.equals(movie.getDescription(), description);
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
