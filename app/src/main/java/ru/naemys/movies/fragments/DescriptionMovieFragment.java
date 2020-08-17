package ru.naemys.movies.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
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

import org.jetbrains.annotations.NotNull;

import ru.naemys.movies.R;
import ru.naemys.movies.models.Movie;

public class DescriptionMovieFragment extends Fragment {
    public static final String TAG = DescriptionMovieFragment.class.getSimpleName();

    private EditText mReviewMovieEditText;
    private CheckBox mLikeMovieCheckBox;

    private Movie mMovie;

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
        return inflater.inflate(R.layout.fragment_description_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (mMovie != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(mMovie.getTitle());

            ((TextView) view.findViewById(R.id.descriptionMovieTextView))
                    .setText(mMovie.getDescription());
            ((ImageView) view.findViewById(R.id.posterMovieImageView))
                    .setImageResource(mMovie.getPosterResource());
        }

        mReviewMovieEditText = view.findViewById(R.id.reviewMovieEditText);
        mLikeMovieCheckBox = view.findViewById(R.id.likeMovieCheckBox);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.app_name);
        String review = mReviewMovieEditText.getText().toString().trim();
        boolean likeMovie = mLikeMovieCheckBox.isChecked();

        Log.d(TAG, "Like movie: " + likeMovie + ", review: " + review);
    }

    private void setMovie(Movie movie) {
        mMovie = movie;
    }
}
