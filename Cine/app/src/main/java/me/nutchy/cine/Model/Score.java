package me.nutchy.cine.Model;

/**
 * Created by nutchy on 22/11/2017 AD.
 */

public class Score {
    private int score;
    private String movieId, uid;

    public Score(){}

    public Score(int score, String movieId, String uid) {
        this.score = score;
        this.movieId = movieId;
        this.uid = uid;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
