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

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);

        posterMovieImageView = itemView.findViewById(R.id.posterMovieImageView);
        titleMovieTextView = itemView.findViewById(R.id.titleMovieTextView);
        descriptionMovieButton = itemView.findViewById(R.id.descriptionMovieButton);
    }

    public void bind(@NonNull Movie movie) {
        posterMovieImageView.setImageResource(movie.getPosterResource());
        titleMovieTextView.setText(movie.getTitle());
    }

    public void setOnDescriptionButtonClickListener(
            final MovieAdapter.OnDescriptionButtonClickListener clickListener) {
        descriptionMovieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onDescriptionButtonClick(getAdapterPosition());
            }
        });
    }
}
