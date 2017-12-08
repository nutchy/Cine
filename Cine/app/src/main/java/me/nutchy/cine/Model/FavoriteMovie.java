package me.nutchy.cine.Model;

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
