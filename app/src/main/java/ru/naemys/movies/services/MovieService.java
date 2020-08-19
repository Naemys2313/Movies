package ru.naemys.movies.services;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ru.naemys.movies.models.MoviesJson;

public interface MovieService {
    @GET("api/v2.1/films/top")
    Call<MoviesJson> getMovies(@Query("page") int page);

    @GET("api/v2.1/films/{id}")
    Call<JsonObject> getMovie(@Path("id") String id);
}
