package ru.naemys.movies.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

import ru.naemys.movies.R;
import ru.naemys.movies.models.Movie;

public class DescriptionMovieFragment extends Fragment {
    public static final String EXTRA_MOVIE_TITLE = "EXTRA_MOVIE_TITLE";
    public static final String EXTRA_MOVIE_DESCRIPTION = "EXTRA_MOVIE_DESCRIPTION";
    public static final String EXTRA_MOVIE_POSTER_RESOURCE = "EXTRA_MOVIE_POSTER_RESOURCE";

    @NotNull
    public static DescriptionMovieFragment newInstance(Movie movie) {
        Bundle args = new Bundle();

        args.putString(EXTRA_MOVIE_TITLE, movie.getTitle());
        args.putString(EXTRA_MOVIE_DESCRIPTION, movie.getDescription());
        args.putInt(EXTRA_MOVIE_POSTER_RESOURCE, movie.getPosterResource());

        DescriptionMovieFragment fragment = new DescriptionMovieFragment();
        fragment.setArguments(args);
        return fragment;
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
        if (getArguments() != null) {
            ((TextView) view.findViewById(R.id.titleMovieTextView))
                    .setText(getArguments().getString(EXTRA_MOVIE_TITLE,
                            getString(R.string.title_movie_text_view_mask)));
            ((TextView) view.findViewById(R.id.descriptionMovieTextView))
                    .setText(getArguments().getString(EXTRA_MOVIE_DESCRIPTION,
                            getString(R.string.description_movie_text_view_mask)));
            ((ImageView) view.findViewById(R.id.posterMovieImageView))
                    .setImageResource(getArguments().getInt(EXTRA_MOVIE_POSTER_RESOURCE,
                            R.drawable.ic_baseline_movie_24));
        }
    }
}
