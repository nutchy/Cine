package me.nutchy.cine;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import me.nutchy.cine.Adapter.MoviesAdapter;
import me.nutchy.cine.Api.ConnectionAPI;
import me.nutchy.cine.Api.TmdbApi;
import me.nutchy.cine.Model.Movie;
import me.nutchy.cine.Model.Movies;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        MoviesAdapter.MoviesAdapterListener, ConnectionAPI.ConnectionApiListener {

    ConnectionAPI connectionAPI;
    LinearLayout loadingLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        connectionAPI = ConnectionAPI.getInstance();
        connectionAPI.setListener(this);
        setContentView(R.layout.activity_main);
        loadingLayout = (LinearLayout) this.findViewById(R.id.loading);
        initLayout();
        initContent();
    }

    private void initContent() {
        connectionAPI.getPopularList();
        connectionAPI.getUpcomingList();
        connectionAPI.getNowShowingList();
    }

    @Override
    public void onItemClickListener(Movie movie) {
        prepareMovieDetail(movie);
    }

    private void prepareMovieDetail(Movie movie) {
        loadingLayout.setVisibility(LinearLayout.VISIBLE);
        // Disable Touch
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        connectionAPI.getMovieById(movie.getId());
    }

    private void startMovieDetailActivity(Movie movie) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra("movie", movie);
        startActivity(intent);
    }

    public void initLayout() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Binding for set values
        View headerView = navigationView.getHeaderView(0);
        setUserDetail(headerView);


    }

    public void setUserDetail(View view) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            TextView tv_fullName = view.findViewById(R.id.nav_fullName);
            TextView tv_email = view.findViewById(R.id.nav_email);
            ImageView iv_avatar = view.findViewById(R.id.nav_avatar);

            tv_fullName.setText(user.getDisplayName());
            tv_email.setText(user.getEmail());
            Glide.with(this).load(user.getPhotoUrl().toString()).into(iv_avatar);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Snackbar.make(findViewById(android.R.id.content), "Developed By Chayanon Thongpila", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.logout) {
            userLogout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onMovieResponse(Movie movie) {
        startMovieDetailActivity(movie);
        loadingLayout.setVisibility(LinearLayout.GONE);
        // Enable Touch
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    @Override
    public void onUpcomingResponse(Movies movies) {
        displayUpcomingList(movies);
    }

    @Override
    public void onPopularResponse(Movies movies) {
        displayPopularList(movies);
    }

    @Override
    public void onNowShowingResponse(Movies movies) {
        displayNowShowingList(movies);
    }

    private void displayPopularList(Movies movies) {
        MoviesAdapter moviesAdapter = new MoviesAdapter(movies, this);
        moviesAdapter.setMoviesAdapterListener(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rc_popular_movies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(moviesAdapter);

    }

    private void displayUpcomingList(Movies movies) {
        MoviesAdapter moviesAdapter = new MoviesAdapter(movies, this);
        moviesAdapter.setMoviesAdapterListener(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rc_upcoming_movies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(moviesAdapter);
    }

    private void displayNowShowingList(Movies movies) {
        MoviesAdapter moviesAdapter = new MoviesAdapter(movies, this);
        moviesAdapter.setMoviesAdapterListener(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rc_now_showing);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(moviesAdapter);
    }

    public void userLogout() {
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        finish();
        startActivity(new Intent(this, LoginActivity.class));
    }
}
