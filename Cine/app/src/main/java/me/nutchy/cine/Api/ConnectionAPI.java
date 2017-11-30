package me.nutchy.cine.Api;

import me.nutchy.cine.Model.Movie;
import me.nutchy.cine.Model.Movies;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConnectionAPI {
    private static ConnectionAPI instance;
    private OkHttpClient client;
    private Retrofit retrofit;
    private TmdbApi tmdbApi;
    public interface ConnectionApiListener{
        void onMovieResponse(Movie movie);
        void onUpcomingResponse(Movies movies);
        void onPopularResponse(Movies movies);
        void onNowShowingResponse(Movies movies);
    }

    private ConnectionApiListener listener;

    public void setListener(ConnectionApiListener listener) {
        this.listener = listener;
    }

    private ConnectionAPI (){
        client = new OkHttpClient.Builder().build();
        retrofit = new Retrofit
                .Builder()
                .baseUrl(TmdbApi.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        tmdbApi = retrofit.create(TmdbApi.class);
    }

    public static ConnectionAPI getInstance() {
        if(instance == null){
            instance = new ConnectionAPI();
        }
        return instance;
    }

    public void getMovieById(int movieId){
        Call<Movie> call = tmdbApi.getMovieById(movieId, TmdbApi.API_KEY);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful()){
                    Movie movieResponse = response.body();
                    listener.onMovieResponse(movieResponse);
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
            }
        });
    }

    public void getUpcomingList(){
        Call<Movies> call = tmdbApi.getUpcoming(TmdbApi.API_KEY);
        call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                if(response.isSuccessful()){
                    Movies moviesResponse = response.body();
                    listener.onUpcomingResponse(moviesResponse);
                }
            }
            @Override
            public void onFailure(Call<Movies> call, Throwable t) {

            }
        });
    }

    public void getPopularList(){
        Call<Movies> call = tmdbApi.getPopular(TmdbApi.API_KEY);
        call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                if(response.isSuccessful()){
                    Movies moviesResponse = response.body();
                    listener.onPopularResponse(moviesResponse);
                }
            }
            @Override
            public void onFailure(Call<Movies> call, Throwable t) {

            }
        });
    }

    public void getNowShowingList(){
        Call<Movies> call = tmdbApi.getNowShowing(TmdbApi.API_KEY);
        call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                if(response.isSuccessful()){
                    Movies moviesResponse = response.body();
                    listener.onNowShowingResponse(moviesResponse);
                }
            }
            @Override
            public void onFailure(Call<Movies> call, Throwable t) {

            }
        });
    }
}
