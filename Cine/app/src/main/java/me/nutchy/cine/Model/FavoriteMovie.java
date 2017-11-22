package me.nutchy.cine.Model;

/**
 * Created by nutchy on 22/11/2017 AD.
 */

public class FavoriteMovie {
    private int movieId;

    public FavoriteMovie(){}

    public FavoriteMovie(int movieId) {
        this.movieId = movieId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

}
