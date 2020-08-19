package ru.naemys.movies.models;

import androidx.annotation.Nullable;

import java.util.Objects;

public class Movie {
    private String id;
    private String title;
    private String description;
    private String posterUrl;
    private boolean favorite = false;

    public Movie() {
    }

    public Movie(String id, String title, String description, String posterUrl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.posterUrl = posterUrl;
    }

    public Movie(String id, String title, String description, String posterUrl, boolean favorite) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.posterUrl = posterUrl;
        this.favorite = favorite;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
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

        return Objects.equals(movie.getPosterUrl(), posterUrl)
                && Objects.equals(movie.getTitle(), title)
                && Objects.equals(movie.getDescription(), description);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", posterUrl='" + posterUrl + '\'' +
                ", favorite=" + favorite +
                '}';
    }
}
