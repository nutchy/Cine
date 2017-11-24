package me.nutchy.cine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.support.v7.widget.Toolbar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import me.nutchy.cine.Adapter.CommentsAdapter;
import me.nutchy.cine.Model.Comment;
import me.nutchy.cine.Model.FavoriteMovie;
import me.nutchy.cine.Model.FavoriteMovieList;
import me.nutchy.cine.Model.Movie;
import me.nutchy.cine.Model.Rating;

public class MovieDetailActivity extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private Movie movie;
    private FavoriteMovieList favoriteMovieList;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        movie = intent.getParcelableExtra("movie");
        user = FirebaseAuth.getInstance().getCurrentUser();
        favoriteMovieList = new FavoriteMovieList();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        initToolbar();
        initRatingBar();
        bindRating();
        initComment();
        initFavorite();
        displayPoster();
        displayComment();
    }

    private void bindRating() {
        ImageView cineStar = (ImageView) findViewById(R.id.cineStar);
        ImageView youStar = (ImageView) findViewById(R.id.youStar);
        ImageView imdbStar = (ImageView) findViewById(R.id.imdbStar);
        TextView cineRate = (TextView) findViewById(R.id.tv_cine_rate);
        TextView cineRateCount = (TextView) findViewById(R.id.tv_cine_rateCount);
        TextView userRate = (TextView) findViewById(R.id.tv_your_rate);
        TextView userRateLabel = (TextView) findViewById(R.id.tv_you_label);
        TextView imdbRate = (TextView) findViewById(R.id.tv_imdb_rating);
        TextView imdbRateCount = (TextView) findViewById(R.id.tv_imdb_rateCount);

        imdbRate.setText(String.valueOf(movie.getVote_average()));
        imdbRateCount.setText(String.valueOf(movie.getVote_count()));

    }

    private void initFavorite() {
        favoriteMovieList = new FavoriteMovieList();
        final Button btn_favorite = (Button) findViewById(R.id.btn_favorite);
        DatabaseReference mFavoriteRef = databaseReference.child("favorites")
                .child(String.valueOf(user.getUid()));

        mFavoriteRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                favoriteMovieList.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Movie favoriteMovie = ds.getValue(Movie.class);
                    favoriteMovieList.add(favoriteMovie);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        btn_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickFavorite();
            }
        });

    }

    public void onClickFavorite() {
        DatabaseReference mFavoriteRef = databaseReference.child("favorites")
                .child(String.valueOf(user.getUid()));
        boolean isFavorite = false;
        for(Movie m : favoriteMovieList.getFavoriteMovies()){
            if(m.getId() == movie.getId()) isFavorite = true;
        }
        if(isFavorite){
            // unFavorite this movie
            mFavoriteRef.child(String.valueOf(movie.getId())).setValue(null);
        } else {
            mFavoriteRef.child(String.valueOf(movie.getId())).setValue(movie);
        }
    }

    private void initComment() {
        final EditText et_comment = (EditText) findViewById(R.id.et_comment);
        Button btn_comment = (Button) findViewById(R.id.btn_comment);
        btn_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = et_comment.getText().toString();
                addCommentToFirebase(comment);
            }
        });
    }

    private void initRatingBar() {
        Button ratingBtn = (Button) findViewById(R.id.btn_rating);
        final TextView ratingTv = (TextView) findViewById(R.id.tv_rating);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.rating_bar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingTv.setText(String.valueOf((int) rating));
            }
        });
        ratingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rating = Integer.parseInt(ratingTv.getText().toString());
                addRatingToFirebase(rating);
            }
        });
    }

    private void addRatingToFirebase(int rating) {
        DatabaseReference mRatingRef = databaseReference
                .child("ratings");
        String key = mRatingRef.child(String.valueOf(movie.getId())).push().getKey();
        Rating mRating = new Rating(user.getUid(), movie.getId(), rating);
        mRatingRef.child(String.valueOf(movie.getId())).child(key).setValue(mRating);

        Toast.makeText(MovieDetailActivity.this
                , String.valueOf(rating), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void displayPoster() {
        ImageView iV_poster = (ImageView) findViewById(R.id.iV_poster);
        Glide.with(this).load(movie.BASE_URL_POSTER + movie.getPoster_path())
                .into(iV_poster);
    }

    public void displayComment() {
        CommentsAdapter commentsAdapter = new CommentsAdapter(this, movie);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rc_comments);
        recyclerView.setLayoutManager(new LinearLayoutManager
                (this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(commentsAdapter);
    }

    public void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        toolbar.setTitle(movie.getTitle());
    }

    public void addCommentToFirebase(String comment) {
        DatabaseReference mCommentRef = databaseReference.child("comments");
        String key = mCommentRef.child(String.valueOf(movie.getId())).push().getKey();
        Comment mComment = new Comment(
                comment, user.getDisplayName(), user.getUid(), movie.getId(), key);
        mCommentRef.child(String.valueOf(movie.getId())).child(key).setValue(mComment);
        Toast.makeText(MovieDetailActivity.this, "Comment Added.", Toast.LENGTH_SHORT)
                .show();
    }


}
