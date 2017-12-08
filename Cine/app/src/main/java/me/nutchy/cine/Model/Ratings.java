package me.nutchy.cine.Model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Ratings {
    private List<Rating> ratings;
    public Ratings(){
        ratings = new ArrayList<>();
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public void clear(){
        this.ratings.clear();
    }

    public void add(Rating rating){
        this.ratings.add(rating);
    }

    public String getCount(){
        if(ratings.size() != 0 && ratings != null) {
            return String.valueOf(ratings.size());
        } else {
            return "0";
        }
    }

    public String getAverage(){
        if(ratings.size() != 0 && ratings != null) {
            float total = 0;
            for (Rating r : ratings) {
                total += (float) r.getRating();
            }
            float avg = total / (float) ratings.size();
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);
            return String.valueOf(df.format(avg)+"/10");
        } else {
            return "0/10";
        }
    }
}
