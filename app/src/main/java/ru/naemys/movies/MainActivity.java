package ru.naemys.movies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ShareCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import ru.naemys.movies.fragments.AddMovieFragment;
import ru.naemys.movies.fragments.DescriptionMovieFragment;
import ru.naemys.movies.fragments.MoviesFragment;
import ru.naemys.movies.models.Movie;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private MoviesFragment mMoviesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mMoviesFragment = new MoviesFragment();
        mMoviesFragment.setOnDescriptionButtonCLickListener(
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
                .replace(R.id.fragmentContainer, mMoviesFragment, MoviesFragment.TAG)
                .commit();


        ((NavigationView) findViewById(R.id.navigationView))
                .setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.inviteFriendItemMenu:
                inviteFriend();
                return true;

            case R.id.addMovieItemMenu:
                addMovie();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void inviteFriend() {
        ShareCompat.IntentBuilder.from(this)
                .setChooserTitle(R.string.invite_friend_chooser_title)
                .setType("text/plain")
                .setText(getString(R.string.invite_friend_text))
                .startChooser();
    }

    private void addMovie() {
        AddMovieFragment addMovieFragment = new AddMovieFragment();
        addMovieFragment.setOnAddMovieClickListener(new AddMovieFragment.OnAddMovieClickListener() {
            @Override
            public void onAddMovieClick(Movie movie) {
                mMoviesFragment.addMovie(movie);
                getSupportFragmentManager().popBackStack();
            }
        });

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, addMovieFragment, AddMovieFragment.TAG)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mainScreenItemMenu:
                getSupportFragmentManager().popBackStack();
                break;
            case R.id.aboutAppItemMenu:
                Toast.makeText(this, "О приложении", Toast.LENGTH_SHORT).show();
                break;
            case R.id.exitItemMenu:
                exitFromApp();
                break;
        }

        ((DrawerLayout) findViewById(R.id.drawerLayout)).closeDrawer(GravityCompat.START);
        return true;
    }

    private void exitFromApp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setTitle(R.string.exit_from_app_alert_dialog_title);
        builder.show();
    }
}