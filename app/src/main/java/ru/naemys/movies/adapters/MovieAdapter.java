package ru.naemys.movies.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.naemys.movies.R;
import ru.naemys.movies.holders.MovieViewHolder;
import ru.naemys.movies.models.Movie;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_MOVIE = 1;
    private static final int VIEW_TYPE_FOOTER = 2;

    private boolean favorite = false;

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

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_FOOTER && !favorite) {
            return new SimpleViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_movie_footer, parent, false));
        } else {
            return new MovieViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_movie, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MovieViewHolder) {
            MovieViewHolder movieViewHolder = (MovieViewHolder) holder;
            movieViewHolder.bind(movies.get(position));
            movieViewHolder.setOnDescriptionButtonClickListener(onDescriptionButtonClickListener);
            movieViewHolder.setOnFavoriteMovieClickListener(onFavoriteMovieClickListener);
        }
    }

    @Override
    public int getItemCount() {
        return movies.size() + (favorite ? 0 : 1);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) return VIEW_TYPE_FOOTER;
        else return VIEW_TYPE_MOVIE;
    }

    static class SimpleViewHolder extends RecyclerView.ViewHolder {

        public SimpleViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
