package me.nutchy.cine.Model;

import java.util.ArrayList;
import java.util.List;

public class FavoriteMovieList {
    private List<Movie> favoriteMovies = new ArrayList<>();

    public FavoriteMovieList(){

    }

    public FavoriteMovieList(List<Movie> favoriteMovies) {
        this.favoriteMovies = favoriteMovies;
    }
    public void clear(){
        favoriteMovies.clear();
    }

    public void add(Movie favoriteMovie){
        this.favoriteMovies.add(favoriteMovie);
    }
    public void removeByIndex(int index){
        this.favoriteMovies.remove(index);
    }

    public List<Movie> getFavoriteMovies() {
        return favoriteMovies;
    }
    public void remove(Movie fm){
        favoriteMovies.remove(fm);
    }

    public void removeByMovieId(int id) {
        for(Movie fm: favoriteMovies){
            if(fm.getId()==id){
                favoriteMovies.remove(fm);
            }
        }
    }
}
