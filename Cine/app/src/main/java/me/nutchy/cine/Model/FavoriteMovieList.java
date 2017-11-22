package me.nutchy.cine.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nutchy on 23/11/2017 AD.
 */

public class FavoriteMovieList {
    private List<FavoriteMovie> favoriteMovies = new ArrayList<>();

    public FavoriteMovieList(){

    }

    public FavoriteMovieList(List<FavoriteMovie> favoriteMovies) {
        this.favoriteMovies = favoriteMovies;
    }
    public void clear(){
        favoriteMovies.clear();
    }

    public void add(FavoriteMovie favoriteMovie){
        this.favoriteMovies.add(favoriteMovie);
    }
    public void removeByIndex(int index){
        this.favoriteMovies.remove(index);
    }

    public List<FavoriteMovie> getFavoriteMovies() {
        return favoriteMovies;
    }
    public void remove(FavoriteMovie fm){
        favoriteMovies.remove(fm);
    }

    public void removeByMovieId(int id) {
        for(FavoriteMovie fm: favoriteMovies){
            if(fm.getMovieId().equals(String.valueOf(id))){
                fm.setMovieId(null);
            }
        }
    }
}
