package me.nutchy.cine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.ButterKnife;
import me.nutchy.cine.Model.Comment;
import me.nutchy.cine.Model.Movie;

public class MovieDetailActivity extends AppCompatActivity {

    private String TAG_TITLE = "MOVIE_TITLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        final Movie movie = intent.getParcelableExtra("movie");
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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
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
