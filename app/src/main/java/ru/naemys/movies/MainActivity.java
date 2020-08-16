package ru.naemys.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toolbar;

import ru.naemys.movies.fragments.DescriptionMovieFragment;
import ru.naemys.movies.fragments.MoviesFragment;
import ru.naemys.movies.models.Movie;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setActionBar((Toolbar) findViewById(R.id.toolbar));

        MoviesFragment moviesFragment = new MoviesFragment();
        moviesFragment.setOnDescriptionButtonCLickListener(
                new MoviesFragment.OnDescriptionButtonClickListener() {
                    @Override
                    public void onDescriptionButtonClick(Movie movie) {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragmentContainer,
                                        DescriptionMovieFragment.newInstance(movie),
                                        DescriptionMovieFragment.TAG)
                                .addToBackStack(null)
                                .commit();
                    }
                });

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, moviesFragment, MoviesFragment.TAG)
                .commit();
    }
}