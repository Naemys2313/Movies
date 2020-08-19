package ru.naemys.movies.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.naemys.movies.R;
import ru.naemys.movies.holders.MovieViewHolder;
import ru.naemys.movies.models.Movie;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {
    private List<Movie> movies = new ArrayList<>();

    private OnDescriptionButtonClickListener onDescriptionButtonClickListener;
    private OnFavoriteMovieClickListener onFavoriteMovieClickListener;

    public interface OnDescriptionButtonClickListener {
        void onDescriptionButtonClick(int position);
    }

    public interface OnFavoriteMovieClickListener {
        void onFavoriteMovieClick(int position);
    }

    public void setOnDescriptionButtonClickListener(
            OnDescriptionButtonClickListener onDescriptionButtonClickListener) {
        this.onDescriptionButtonClickListener = onDescriptionButtonClickListener;
    }

    public void setOnFavoriteMovieClickListener(OnFavoriteMovieClickListener onFavoriteMovieClickListener) {
        this.onFavoriteMovieClickListener = onFavoriteMovieClickListener;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(movies.get(position));
        holder.setOnDescriptionButtonClickListener(onDescriptionButtonClickListener);
        holder.setOnFavoriteMovieClickListener(onFavoriteMovieClickListener);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
