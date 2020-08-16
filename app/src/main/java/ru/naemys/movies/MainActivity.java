package ru.naemys.movies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ShareCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import ru.naemys.movies.fragments.DescriptionMovieFragment;
import ru.naemys.movies.fragments.MoviesFragment;
import ru.naemys.movies.models.Movie;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        if (item.getItemId() == R.id.inviteFriendItemMenu) {
            inviteFriend();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void inviteFriend() {
        ShareCompat.IntentBuilder.from(this)
                .setChooserTitle(R.string.invite_friend_chooser_title)
                .setType("text/plain")
                .setText(getString(R.string.invite_friend_text))
                .startChooser();
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
        }

        ((DrawerLayout) findViewById(R.id.drawerLayout)).closeDrawer(GravityCompat.START);
        return true;
    }
}