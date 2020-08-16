package ru.naemys.movies;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import ru.naemys.movies.models.Movie;
import ru.naemys.movies.units.MoviesUnit;

public class MoviesRepository {
    private MoviesRepository() {

    }

    @NotNull
    public static List<Movie> getMovies() {
        List<Movie> movies = new ArrayList<>();

        movies.add(new Movie(MoviesUnit.TITLE_MOVIE_1,
                MoviesUnit.DETAIL_MOVIE_1,
                MoviesUnit.POSTER_RESOURCE_MOVIE_1));
        movies.add(new Movie(MoviesUnit.TITLE_MOVIE_2,
                MoviesUnit.DETAIL_MOVIE_2,
                MoviesUnit.POSTER_RESOURCE_MOVIE_2));
        movies.add(new Movie(MoviesUnit.TITLE_MOVIE_3,
                MoviesUnit.DETAIL_MOVIE_3,
                MoviesUnit.POSTER_RESOURCE_MOVIE_3));
        movies.add(new Movie(MoviesUnit.TITLE_MOVIE_4,
                MoviesUnit.DETAIL_MOVIE_4,
                MoviesUnit.POSTER_RESOURCE_MOVIE_4));
        movies.add(new Movie(MoviesUnit.TITLE_MOVIE_5,
                MoviesUnit.DETAIL_MOVIE_5,
                MoviesUnit.POSTER_RESOURCE_MOVIE_5));

        return movies;
    }
}
