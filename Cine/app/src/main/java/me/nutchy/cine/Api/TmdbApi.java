package me.nutchy.cine.Api;

import me.nutchy.cine.Model.Movies;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TmdbApi {
//    Host
    String API_KEY = "7a9375b362fd6eaccb0c036bfa16b282";
    String BASE_URL = "https://api.themoviedb.org/";

    @GET("3/movie/upcoming")
    Call<Movies> getUpcoming(@Query("api_key") String api_key);

    @GET("3/movie/popular")
    Call<Movies> getPopular(@Query("api_key") String api_key);


}
