package me.nutchy.cine.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by nutchy on 18/11/2017 AD.
 */

public class Movies implements Parcelable {
    private List<Movie> results;

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.results);
    }

    public Movies() {
    }

    protected Movies(Parcel in) {
        this.results = in.createTypedArrayList(Movie.CREATOR);
    }

    public static final Parcelable.Creator<Movies> CREATOR = new Parcelable.Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel source) {
            return new Movies(source);
        }

        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };
}
