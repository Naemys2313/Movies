package ru.naemys.movies.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.naemys.movies.MoviesRepository;
import ru.naemys.movies.R;
import ru.naemys.movies.adapters.MovieAdapter;
import ru.naemys.movies.models.Movie;

public class MoviesFragment extends Fragment {
    public static final String TAG = MoviesFragment.class.getSimpleName();

    private List<Movie> mMovies = new ArrayList<>();
    private RecyclerView mMovieRecyclerView;
    private OnDescriptionButtonClickListener mOnDescriptionButtonClickListener;

    public interface OnDescriptionButtonClickListener {
        void onDescriptionButtonClick(Movie movie);
    }

    public void setOnDescriptionButtonCLickListener(
            OnDescriptionButtonClickListener clickListener) {
        this.mOnDescriptionButtonClickListener = clickListener;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        mMovies.addAll(MoviesRepository.getMovies());
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

        ((MovieAdapter) mMovieRecyclerView.getAdapter()).setOnDescriptionButtonClickListener(
                new MovieAdapter.OnDescriptionButtonClickListener() {
                    @Override
                    public void onDescriptionButtonClick(int position) {
                        mOnDescriptionButtonClickListener.onDescriptionButtonClick(
                                mMovies.get(position));
                    }
                });
    }

    private void attachMovieAdapter() {
        MovieAdapter movieAdapter = new MovieAdapter();
        movieAdapter.addMovies(mMovies);

        mMovieRecyclerView.setAdapter(movieAdapter);
        mMovieRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mMovieRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
    }

    public void setContentChange() {
        mMovieRecyclerView.getAdapter().notifyDataSetChanged();
    }
}
