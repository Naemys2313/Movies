package ru.naemys.movies.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.naemys.movies.App;
import ru.naemys.movies.MainActivity;
import ru.naemys.movies.R;
import ru.naemys.movies.adapters.MovieAdapter;
import ru.naemys.movies.models.Movie;
import ru.naemys.movies.models.MoviesJson;

public class MoviesFragment extends Fragment {
    public static final String TAG_MOVIES = MoviesFragment.class.getSimpleName();
    public static final String TAG_FAVORITE_MOVIES = MoviesFragment.class.getSimpleName() + "Favorite";

    public static final String EXTRA_SHOW_FAVORITE = "EXTRA_SHOW_FAVORITE";

    private List<Movie> mMovies = new ArrayList<>();

    private RecyclerView mMovieRecyclerView;
    private MovieAdapter mMovieAdapter;

    private OnDescriptionButtonClickListener mOnDescriptionButtonClickListener;

    private SharedPreferences mSharedPreferences;

    private int mPage = 1;

    public interface OnDescriptionButtonClickListener {
        void onDescriptionButtonClick(Movie movie);
    }

    @NotNull
    public static MoviesFragment newInstance(boolean showFavorite) {
        Bundle args = new Bundle();
        args.putBoolean(EXTRA_SHOW_FAVORITE, showFavorite);

        MoviesFragment fragment = new MoviesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void setOnDescriptionButtonClickListener(
            OnDescriptionButtonClickListener clickListener) {
        this.mOnDescriptionButtonClickListener = clickListener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferences = getActivity().getSharedPreferences(
                MainActivity.SHARED_PREFERENCES_MOVIES, Context.MODE_PRIVATE);

        createMovieAdapter();

        if (isShowFavorite())
            mMovies.addAll(getFavoriteMoviesFromSharedPreferences());
        else
            loadMovies();

        if (isShowFavorite())
            ((AppCompatActivity) getActivity()).getSupportActionBar()
                    .setTitle(R.string.favorite_movie_screen_item_menu);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mMovieRecyclerView = view.findViewById(R.id.moviesRecyclerView);
        attachMovieAdapter();

        mMovieRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager layoutManager =
                        (LinearLayoutManager) recyclerView.getLayoutManager();
                if (layoutManager == null) return;

                if (layoutManager.findLastCompletelyVisibleItemPosition() == mMovies.size()) {
                    mPage++;
                    loadMovies();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (isShowFavorite())
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.app_name);
    }

    private void createMovieAdapter() {
        mMovieAdapter = new MovieAdapter();
        mMovieAdapter.setMovies(mMovies);
        mMovieAdapter.setOnDescriptionButtonClickListener(
                new MovieAdapter.OnDescriptionButtonClickListener() {
                    @Override
                    public void onDescriptionButtonClick(int position) {
                        mOnDescriptionButtonClickListener.onDescriptionButtonClick(
                                mMovies.get(position));
                    }
                });

        mMovieAdapter.setOnFavoriteMovieClickListener(
                new MovieAdapter.OnFavoriteMovieClickListener() {
                    @Override
                    public void onFavoriteMovieClick(int position) {
                        Movie movie = mMovies.get(position);
                        movie.setFavorite(!movie.isFavorite());

                        if (movie.isFavorite()) {
                            saveFavoriteMovie(movie);
                        } else {
                            removeFavoriteMovie(movie);
                        }

                        mMovieAdapter.notifyItemChanged(position);
                    }
                });
    }

    private void attachMovieAdapter() {
        mMovieRecyclerView.setAdapter(mMovieAdapter);
        mMovieRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mMovieRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
    }

    private void setFavoriteMovies() {
        ArrayList<Movie> movies = (ArrayList<Movie>) getFavoriteMoviesFromSharedPreferences();

        for (Movie movie : mMovies) {
            if (movies.contains(movie)) {
                movie.setFavorite(true);
            } else {
                movie.setFavorite(false);
            }
        }

        mMovieAdapter.notifyDataSetChanged();
    }

    public void saveFavoriteMovie(Movie movie) {
        Gson gson = new Gson();

        List<Movie> movies = getFavoriteMoviesFromSharedPreferences();

        movies.add(movie);

        String moviesJson = gson.toJson(movies);

        mSharedPreferences.edit().putString(
                MainActivity.SHARED_PREFERENCES_MOVIES_ARRAY, moviesJson).apply();

    }

    public void removeFavoriteMovie(Movie movie) {
        Gson gson = new Gson();

        ArrayList<Movie> movies = (ArrayList<Movie>) getFavoriteMoviesFromSharedPreferences();

        if (movies.contains(movie)) {
            movies.remove(movie);
        }

        String moviesJson = gson.toJson(movies);

        mSharedPreferences.edit().putString(
                MainActivity.SHARED_PREFERENCES_MOVIES_ARRAY, moviesJson).apply();
    }

    @NotNull
    private List<Movie> getFavoriteMoviesFromSharedPreferences() {
        Gson gson = new Gson();

        List<Movie> movies = new ArrayList<>();

        String moviesJson = mSharedPreferences.getString(
                MainActivity.SHARED_PREFERENCES_MOVIES_ARRAY, null);

        if (moviesJson != null) {
            Movie[] moviesArray = gson.fromJson(moviesJson, Movie[].class);
            Collections.addAll(movies, moviesArray);
        }

        return movies;
    }

    public void setContentChange() {
        mMovieRecyclerView.getAdapter().notifyDataSetChanged();
    }

    public void addMovie(Movie movie) {
        mMovies.add(0, movie);
        mMovieRecyclerView.getAdapter().notifyItemInserted(0);
    }

    private boolean isShowFavorite() {
        return getArguments() != null
                && getArguments().containsKey(EXTRA_SHOW_FAVORITE)
                && getArguments().getBoolean(EXTRA_SHOW_FAVORITE, false);
    }

    private void loadMovies() {
        App.getInstance().getMovieService().getMovies(mPage).enqueue(new Callback<MoviesJson>() {
            @Override
            public void onResponse(Call<MoviesJson> call, Response<MoviesJson> response) {

                if (response.isSuccessful()) {
                    MoviesJson moviesJson = response.body();
                    for (MoviesJson.MovieJson movieJson : moviesJson.movieJsonList) {
                        mMovies.add(new Movie(movieJson.id, movieJson.title, "Some Description", movieJson.posterUrl));
                    }
                    mMovieAdapter.notifyDataSetChanged();

                    setFavoriteMovies();
                } else {
                    Log.d(TAG_MOVIES, "response.code: " + response.code());
                }

            }

            @Override
            public void onFailure(Call<MoviesJson> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
