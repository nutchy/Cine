package me.nutchy.cine.Api;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;

import me.nutchy.cine.Model.Movie;
import me.nutchy.cine.Model.Movies;

/**
 * Created by nutchy on 2/12/2017 AD.
 */

public class FavoritesResponse {
    private FavoriteResponseListener listener;
    public interface FavoriteResponseListener{
        void onFavoriteResponse(Movies movies);
    }

    private static FavoritesResponse instance;

    public FavoriteResponseListener getListener() {
        return listener;
    }

    public void setListener(FavoriteResponseListener listener) {
        this.listener = listener;
    }

    private FavoritesResponse() { }

    public static FavoritesResponse getInstance(){
        if (instance == null){
            instance = new FavoritesResponse();
        }
        return instance;
    }

    public void getFavoriteList(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Log.e("TOTAL FAV", String.valueOf(user.getDisplayName()));
        ref.child("favorites").child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Movie> moviesList = new ArrayList<>();
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    Movie movie = ds.getValue(Movie.class);
                    if (movie != null){
                        moviesList.add(movie);
                    }
                }
                listener.onFavoriteResponse(new Movies(moviesList));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
