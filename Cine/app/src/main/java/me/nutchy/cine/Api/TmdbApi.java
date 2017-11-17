package me.nutchy.cine.Api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TmdbApi {
//    Host
    String API_KEY = "7a9375b362fd6eaccb0c036bfa16b282";
    String BASE_URL = "https://api.themoviedb.org";

    @GET("/upcoming")
    Call<String> getUpcoming(@Query("api_key") String api_key);

    @GET("3/movie/upcoming?api_key=7a9375b362fd6eaccb0c036bfa16b282")
    Call<String> getUpcoming();
}
