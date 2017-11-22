package me.nutchy.cine.Model;

/**
 * Created by nutchy on 22/11/2017 AD.
 */

public class FavoriteMovie {
    private String movieId;

    public FavoriteMovie(){}

    public FavoriteMovie(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

}
