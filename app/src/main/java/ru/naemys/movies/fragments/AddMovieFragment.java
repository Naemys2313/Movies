package ru.naemys.movies.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.naemys.movies.R;
import ru.naemys.movies.models.Movie;

public class AddMovieFragment extends Fragment {
    public static final String TAG = AddMovieFragment.class.getSimpleName();

    private OnAddMovieClickListener onAddMovieClickListener;

    public interface OnAddMovieClickListener {
        void onAddMovieClick(Movie movie);
    }

    public void setOnAddMovieClickListener(OnAddMovieClickListener onAddMovieClickListener) {
        this.onAddMovieClickListener = onAddMovieClickListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        view.findViewById(R.id.addMovieButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Movie movie = new Movie();
                movie.setTitle(((EditText) view.findViewById(R.id.titleMovieEditText))
                        .getText().toString().trim());
                movie.setDescription(((EditText) view.findViewById(R.id.descriptionMovieEditText))
                        .getText().toString().trim());
                movie.setPosterResource(R.drawable.ic_baseline_movie_24);

                Log.d(TAG, "Movie: " + movie.toString());

                onAddMovieClickListener.onAddMovieClick(movie);
            }
        });
    }
}
