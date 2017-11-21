package me.nutchy.cine.Model;

/**
 * Created by nutchy on 22/11/2017 AD.
 */

public class Rating {
    private String uid;
    private int rating, movieId;

    public Rating(){}

    public Rating(String uid, int movieId, int rating) {
        this.uid = uid;
        this.movieId = movieId;
        this.rating = rating;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
