package me.nutchy.cine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.util.Log;
import android.view.View;
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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import me.nutchy.cine.Adapter.CommentsAdapter;
import me.nutchy.cine.Model.Comment;
import me.nutchy.cine.Model.Movie;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Intent intent = getIntent();
        final Movie movie = intent.getParcelableExtra("movie");

        initToolbar();
        initRatingBar();
        displayPoster(movie);
        displayComment(movie);

        final EditText et_comment = (EditText) findViewById(R.id.et_comment);
        Button btn_comment = (Button) findViewById(R.id.btn_comment);
        btn_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = et_comment.getText().toString();
                addCommentToFirebase(comment, movie);
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
                ratingTv.setText(String.valueOf((int)rating));
            }
        });
        ratingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MovieDetailActivity.this,
                        ratingTv.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void displayPoster(Movie movie){
        ImageView iV_poster = (ImageView) findViewById(R.id.iV_poster);
        Glide.with(this).load(movie.BASE_URL_POSTER + movie.getPoster_path())
                .into(iV_poster);
    }

    public void displayComment(Movie movie){
        CommentsAdapter commentsAdapter = new CommentsAdapter(this, movie);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rc_comments);
        recyclerView.setLayoutManager(new LinearLayoutManager
                (this,LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(commentsAdapter);
    }

    public void initToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    public void addCommentToFirebase(String comment, Movie movie) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference mCommentRef = databaseReference.child("comments");

        String key = mCommentRef.child(String.valueOf(movie.getId())).push().getKey();
        Comment mComment = new Comment(
                comment, user.getDisplayName(), user.getUid(), movie.getId(), key);
        mCommentRef.child(String.valueOf(movie.getId())).child(key).setValue(mComment);
        Toast.makeText(MovieDetailActivity.this, "Comment Added.", Toast.LENGTH_SHORT)
                .show();
    }
}
