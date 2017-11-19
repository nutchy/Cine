package me.nutchy.cine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import butterknife.ButterKnife;
import me.nutchy.cine.Model.Movie;

public class MovieDetailActivity extends AppCompatActivity {

    private String TAG_TITLE = "MOVIE_TITLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Movie movie = intent.getParcelableExtra("movie");
        Log.e(TAG_TITLE, movie.getTitle());

    }
}
