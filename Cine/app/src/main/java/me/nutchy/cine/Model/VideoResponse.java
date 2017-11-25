package me.nutchy.cine.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nutchy on 25/11/2017 AD.
 */

public class VideoResponse implements Parcelable {
    private List<Video> results;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.results);
    }

    public VideoResponse() {
    }

    protected VideoResponse(Parcel in) {
        this.results = new ArrayList<Video>();
        in.readList(this.results, Video.class.getClassLoader());
    }

    public static final Parcelable.Creator<VideoResponse> CREATOR = new Parcelable.Creator<VideoResponse>() {
        @Override
        public VideoResponse createFromParcel(Parcel source) {
            return new VideoResponse(source);
        }

        @Override
        public VideoResponse[] newArray(int size) {
            return new VideoResponse[size];
        }
    };
}
