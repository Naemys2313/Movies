package ru.naemys.movies.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesJson {
    @SerializedName("films")
    public List<MovieJson> movieJsonList;

    public class MovieJson {
        @SerializedName("filmId")
        public String id;
        @SerializedName("nameRu")
        public String title;
        @SerializedName("posterUrlPreview")
        public String posterUrl;
    }
}
