package me.nutchy.cine;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import butterknife.ButterKnife;
import me.nutchy.cine.Adapter.CommentsAdapter;
import me.nutchy.cine.Api.ConnectionAPI;
import me.nutchy.cine.Api.TmdbApi;
import me.nutchy.cine.Model.Comment;
import me.nutchy.cine.Model.FavoriteMovie;
import me.nutchy.cine.Model.FavoriteMovieList;
import me.nutchy.cine.Model.Movie;
import me.nutchy.cine.Model.Movies;
import me.nutchy.cine.Model.Rating;
import me.nutchy.cine.Model.Ratings;


public class MovieDetailActivity extends AppCompatActivity {
    DatabaseReference databaseReference, mRatingUserRef;
    private Movie movie;
    private Ratings ratings;
    private FavoriteMovieList favoriteMovieList;
    private FirebaseUser user;
    ImageView cineStar, youStar, imdbStar;
    TextView cineRate, cineRateCount, userRate, userRateLabel, imdbRate, imdbRateCount;
    int userRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        user = FirebaseAuth.getInstance().getCurrentUser();
        movie = intent.getParcelableExtra("movie");
        favoriteMovieList = new FavoriteMovieList();
        ratings = new Ratings();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        initToolbar();
        initRating();
        bindMovieDetail();
        initComment();
        displayPoster();
        displayComment();
        displayUserRaing();
        displayMovieRating();
    }

    private void displayMovieRating() {
        DatabaseReference mRatingMovieRef = databaseReference.child("ratings").child(String.valueOf(movie.getId()));
        mRatingMovieRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ratings.getRatings().clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Rating rating = ds.getValue(Rating.class);
                    ratings.add(rating);
                }
                cineRate.setText(ratings.getAverage());
                cineRateCount.setText(ratings.getCount());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void bindMovieDetail() {
        ImageView iV_poster_mDetail = (ImageView) findViewById(R.id.iV_poster_mDetail);
        Glide.with(this).load(movie.BASE_URL_POSTER + movie.getPoster_path())
                .into(iV_poster_mDetail);
        TextView tv_lang = (TextView) findViewById(R.id.tV_lang);
        TextView tv_release = (TextView) findViewById(R.id.tV_release);
        TextView tv_genre = (TextView) findViewById(R.id.tV_genre);
        TextView tv_overview = (TextView) findViewById(R.id.tV_overview);

        tv_lang.setText(movie.getOriginal_language());
        tv_release.setText(movie.getRelease_date());
        tv_overview.setText(movie.getOverview());
        tv_genre.setText(movie.getAllGenre());

    }

    private void displayUserRaing() {
        mRatingUserRef = databaseReference.child("user-ratings").child(String.valueOf(movie.getId()))
                .child(user.getUid());
        mRatingUserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Rating rating = ds.getValue(Rating.class);
                    Log.e("RATE >>>>>>>>>>>>> ", rating.getRating() + "");
                    userRate.setText(String.valueOf(rating.getRating() + "/10"));
                    userRating = rating.getRating();
                    youStar.setImageResource(R.drawable.ic_star_you);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void initRating() {
        cineStar = (ImageView) findViewById(R.id.cineStar);
        youStar = (ImageView) findViewById(R.id.youStar);
        imdbStar = (ImageView) findViewById(R.id.imdbStar);
        cineRate = (TextView) findViewById(R.id.tv_cine_rate);
        cineRateCount = (TextView) findViewById(R.id.tv_cine_rateCount);
        userRate = (TextView) findViewById(R.id.tv_your_rate);
        userRateLabel = (TextView) findViewById(R.id.tv_you_label);
        imdbRate = (TextView) findViewById(R.id.tv_imdb_rating);
        imdbRateCount = (TextView) findViewById(R.id.tv_imdb_rateCount);

        Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/cine-apps.appspot.com/o/New-imdb-logo.png?alt=media&token=527daf19-881e-48ca-8f99-a32a9112a6be").into(imdbStar);
        imdbRate.setText(String.valueOf(movie.getVote_average()));
        imdbRateCount.setText(String.valueOf(movie.getVote_count()));

        if (userRating == 0) {
            youStar.setImageResource(R.drawable.ic_star_border);
        }
        youStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(MovieDetailActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setTitle("How would you rate " + movie.getTitle());
                dialog.setContentView(R.layout.rating_dialog);
                dialog.setCancelable(true);
                Button ratingBtn = dialog.findViewById(R.id.btn_rating);
                final TextView ratingTv = dialog.findViewById(R.id.tv_rating);
                ratingTv.setText(String.valueOf(userRating));
                RatingBar ratingBar = dialog.findViewById(R.id.rating_bar);
                ratingBar.setRating(userRating);
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
                        Toast.makeText(getApplicationContext(), "Your rating saved.", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });

    }

    private void initFavorite(final MenuItem item) {
        favoriteMovieList = new FavoriteMovieList();
        DatabaseReference mFavoriteRef = databaseReference.child("favorites")
                .child(String.valueOf(user.getUid()));

        mFavoriteRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                favoriteMovieList.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Movie favoriteMovie = ds.getValue(Movie.class);
                    favoriteMovieList.add(favoriteMovie);
                    if (favoriteMovie.getId() == movie.getId()) {
                        item.setIcon(getResources().getDrawable(R.drawable.ic_favorite));
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void onClickFavorite(MenuItem item) {
        DatabaseReference mFavoriteRef = databaseReference.child("favorites")
                .child(String.valueOf(user.getUid()));
        Boolean isFavorite = false;
        for (Movie m : favoriteMovieList.getFavoriteMovies()) {
            if (m.getId() == movie.getId()) {
                isFavorite = true;
            }
        }
        if (isFavorite) {
            // unFavorite this movie
            mFavoriteRef.child(String.valueOf(movie.getId())).setValue(null);
            item.setIcon(getResources().getDrawable(R.drawable.ic_favorite_border));
            Toast.makeText(this, "Removed from Favorite", Toast.LENGTH_SHORT).show();

        } else {
            mFavoriteRef.child(String.valueOf(movie.getId())).setValue(movie);
            item.setIcon(getResources().getDrawable(R.drawable.ic_favorite));
            Toast.makeText(this, "Added to Favorite ❤️", Toast.LENGTH_SHORT).show();
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

    private void addRatingToFirebase(int rating) {
        DatabaseReference mRatingRef = databaseReference
                .child("user-ratings").child(String.valueOf(movie.getId())).child(user.getUid());
        final DatabaseReference mRatingMovieRef = databaseReference.child("ratings").child(String.valueOf(movie.getId()));


        mRatingRef.addListenerForSingleValueEvent(new ValueEventListener() {
            String prevKey = "";

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Rating userRating = ds.getValue(Rating.class);
                    if (userRating != null) {
                        prevKey = userRating.getKey();
                        System.out.println(prevKey);
                        mRatingMovieRef.child(prevKey).setValue(null);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        String key = mRatingRef.push().getKey();
        Rating mRating = new Rating(user.getUid(), movie.getId(), rating, key);
        mRatingRef.setValue(null);
        mRatingRef.child(key).setValue(mRating);
        mRatingMovieRef.child(key).setValue(mRating);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void displayPoster() {
        ImageView iV_poster = (ImageView) findViewById(R.id.iV_poster);
        Glide.with(this).load(movie.BASE_URL_POSTER + movie.getBackdrop_path())
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
                comment, user.getDisplayName(), user.getUid(), movie.getId(), key, user.getPhotoUrl().toString());
        mCommentRef.child(String.valueOf(movie.getId())).child(key).setValue(mComment);
        Toast.makeText(MovieDetailActivity.this, "Comment Added.", Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movie_detail_menu, menu);
        initFavorite(menu.getItem(0));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_favorite) {
            onClickFavorite(item);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
