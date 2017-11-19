package me.nutchy.cine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.support.v7.widget.Toolbar;

import com.bumptech.glide.Glide;

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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        ImageView iV_poster = (ImageView) findViewById(R.id.iV_poster);
        Glide.with(this).load(movie.BASE_URL_POSTER+movie.getPoster_path())
                .into(iV_poster);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
