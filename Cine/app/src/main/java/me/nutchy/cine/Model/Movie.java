package me.nutchy.cine.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Movie implements Parcelable {
    private int vote_count, id, runtime;
    private String title, poster_path, original_language,
            original_title, backdrop_path, release_date, homepage, overview, tagline;
    public String BASE_URL_POSTER = "https://image.tmdb.org/t/p/w500";
    private boolean video, adult;
    private float vote_average, popularity;
    private List<Genre> genres;
    private List<VideoResponse> videos;


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

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<VideoResponse> getVideos() {
        return videos;
    }

    public void setVideos(List<VideoResponse> videos) {
        this.videos = videos;
    }

    public String getAllGenre(){
        String allGenre = "";
        for (int i=0; i< genres.size();i++) {
            Genre genre = genres.get(i);
            if (i>0){
                allGenre += " ,  "+ genre.getName();
            } else {
                allGenre += genre.getName();
            }
        }
        return allGenre;
    }

    public Movie() {
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.vote_count);
        dest.writeInt(this.id);
        dest.writeInt(this.runtime);
        dest.writeString(this.title);
        dest.writeString(this.poster_path);
        dest.writeString(this.original_language);
        dest.writeString(this.original_title);
        dest.writeString(this.backdrop_path);
        dest.writeString(this.release_date);
        dest.writeString(this.homepage);
        dest.writeString(this.overview);
        dest.writeString(this.tagline);
        dest.writeString(this.BASE_URL_POSTER);
        dest.writeByte(this.video ? (byte) 1 : (byte) 0);
        dest.writeByte(this.adult ? (byte) 1 : (byte) 0);
        dest.writeFloat(this.vote_average);
        dest.writeFloat(this.popularity);
        dest.writeList(this.genres);
        dest.writeList(this.videos);
    }

    protected Movie(Parcel in) {
        this.vote_count = in.readInt();
        this.id = in.readInt();
        this.runtime = in.readInt();
        this.title = in.readString();
        this.poster_path = in.readString();
        this.original_language = in.readString();
        this.original_title = in.readString();
        this.backdrop_path = in.readString();
        this.release_date = in.readString();
        this.homepage = in.readString();
        this.overview = in.readString();
        this.tagline = in.readString();
        this.BASE_URL_POSTER = in.readString();
        this.video = in.readByte() != 0;
        this.adult = in.readByte() != 0;
        this.vote_average = in.readFloat();
        this.popularity = in.readFloat();
        this.genres = new ArrayList<Genre>();
        in.readList(this.genres, Genre.class.getClassLoader());
        this.videos = new ArrayList<VideoResponse>();
        in.readList(this.videos, VideoResponse.class.getClassLoader());
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
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
