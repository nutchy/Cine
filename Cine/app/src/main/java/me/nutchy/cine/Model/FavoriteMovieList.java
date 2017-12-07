package me.nutchy.cine.Model;

import java.util.ArrayList;
import java.util.List;

public class FavoriteMovieList {
    private List<Movie> favoriteMovies = new ArrayList<>();

    public FavoriteMovieList(){

    }

    public void clear(){
        favoriteMovies.clear();
    }

    public void add(Movie favoriteMovie){
        this.favoriteMovies.add(favoriteMovie);
    }

    public List<Movie> getFavoriteMovies() {
        return favoriteMovies;
    }
}
