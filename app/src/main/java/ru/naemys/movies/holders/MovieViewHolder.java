package ru.naemys.movies.holders;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.naemys.movies.R;
import ru.naemys.movies.adapters.MovieAdapter;
import ru.naemys.movies.models.Movie;

public class MovieViewHolder extends RecyclerView.ViewHolder {
    private ImageView posterMovieImageView;
    private TextView titleMovieTextView;
    private Button descriptionMovieButton;
    private ImageView favoriteMovieImageView;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);

        posterMovieImageView = itemView.findViewById(R.id.posterMovieImageView);
        titleMovieTextView = itemView.findViewById(R.id.titleMovieTextView);
        descriptionMovieButton = itemView.findViewById(R.id.descriptionMovieButton);
        favoriteMovieImageView = itemView.findViewById(R.id.favoriteMovieImageView);
    }

    public void bind(@NonNull Movie movie) {
        posterMovieImageView.setImageResource(movie.getPosterResource());
        titleMovieTextView.setText(movie.getTitle());

        if (movie.isFavorite()) {
            favoriteMovieImageView.setImageResource(R.drawable.ic_baseline_favorite_24_red);
        } else {
            favoriteMovieImageView.setImageResource(R.drawable.ic_baseline_favorite_24_gray);
        }
    }

    public void setOnDescriptionButtonClickListener(
            final MovieAdapter.OnDescriptionButtonClickListener clickListener) {
        descriptionMovieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null)
                    clickListener.onDescriptionButtonClick(getAdapterPosition());
            }
        });
    }

    public void setOnFavoriteMovieClickListener(
            final MovieAdapter.OnFavoriteMovieClickListener clickListener) {
        favoriteMovieImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.onFavoriteMovieClick(getAdapterPosition());
                }
            }
        });
    }

}
