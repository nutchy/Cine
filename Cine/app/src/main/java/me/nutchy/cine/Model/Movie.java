package me.nutchy.cine.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nutchy on 18/11/2017 AD.
 */

public class Movie implements Parcelable {
    private int vote_count, id;
    private String title, poster_path, original_language,
            original_title, backdrop_path, release_date;
    public String BASE_URL_POSTER = "https://image.tmdb.org/t/p/w500";
    private boolean video, adult;
    private float vote_average, popularity;

    public int getVote_count() {
        return vote_count;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public boolean isVideo() {
        return video;
    }

    public boolean isAdult() {
        return adult;
    }

    public float getVote_average() {
        return vote_average;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.vote_count);
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.poster_path);
        dest.writeString(this.original_language);
        dest.writeString(this.original_title);
        dest.writeString(this.backdrop_path);
        dest.writeString(this.release_date);
        dest.writeString(this.BASE_URL_POSTER);
        dest.writeByte(this.video ? (byte) 1 : (byte) 0);
        dest.writeByte(this.adult ? (byte) 1 : (byte) 0);
        dest.writeFloat(this.vote_average);
        dest.writeFloat(this.popularity);
    }

    public Movie() {
    }

    protected Movie(Parcel in) {
        this.vote_count = in.readInt();
        this.id = in.readInt();
        this.title = in.readString();
        this.poster_path = in.readString();
        this.original_language = in.readString();
        this.original_title = in.readString();
        this.backdrop_path = in.readString();
        this.release_date = in.readString();
        this.BASE_URL_POSTER = in.readString();
        this.video = in.readByte() != 0;
        this.adult = in.readByte() != 0;
        this.vote_average = in.readFloat();
        this.popularity = in.readFloat();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
