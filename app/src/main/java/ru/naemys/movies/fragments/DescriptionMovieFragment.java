package ru.naemys.movies.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import ru.naemys.movies.R;
import ru.naemys.movies.models.Movie;

public class DescriptionMovieFragment extends Fragment {
    public static final String TAG = DescriptionMovieFragment.class.getSimpleName();

    private EditText mReviewMovieEditText;
    private CheckBox mLikeMovieCheckBox;

    private Movie mMovie;

    private String lastTitleActionBar;

    @NotNull
    public static DescriptionMovieFragment newInstance(@NotNull Movie movie) {
        DescriptionMovieFragment fragment = new DescriptionMovieFragment();
        fragment.setMovie(movie);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        return inflater.inflate(R.layout.fragment_description_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (mMovie != null) {
            lastTitleActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar()
                    .getTitle().toString();

            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(mMovie.getTitle());

            ((TextView) view.findViewById(R.id.descriptionMovieTextView))
                    .setText(mMovie.getDescription());

            ImageView posterMovieImageView = view.findViewById(R.id.posterMovieImageView);

            if (mMovie.getPosterUrl() == null) posterMovieImageView
                    .setImageResource(R.drawable.ic_baseline_movie_24);
            else
                Glide.with(getContext())
                        .load(mMovie.getPosterUrl())
                        .into(posterMovieImageView);
        }

        mReviewMovieEditText = view.findViewById(R.id.reviewMovieEditText);
        mLikeMovieCheckBox = view.findViewById(R.id.likeMovieCheckBox);

        mLikeMovieCheckBox.setChecked(mMovie.isFavorite());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(lastTitleActionBar);
        String review = mReviewMovieEditText.getText().toString().trim();
        boolean likeMovie = mLikeMovieCheckBox.isChecked();

        MoviesFragment fragment = ((MoviesFragment) getActivity().getSupportFragmentManager()
                .findFragmentByTag(MoviesFragment.TAG_MOVIES));

        if (likeMovie != mMovie.isFavorite()) {
            if (likeMovie) {
                fragment.saveFavoriteMovie(mMovie);
            } else {
                fragment.removeFavoriteMovie(mMovie);
            }
        }

        mMovie.setFavorite(likeMovie);

        fragment.setContentChange();

        Log.d(TAG, "Like movie: " + likeMovie + ", review: " + review);
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        menu.findItem(R.id.addMovieItemMenu).setVisible(false);
    }

    private void setMovie(Movie movie) {
        mMovie = movie;
    }
}
