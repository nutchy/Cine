package me.nutchy.cine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import me.nutchy.cine.Adapter.MoviesAdapter;
import me.nutchy.cine.Api.TmdbApi;
import me.nutchy.cine.Model.Movies;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class UpcomingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming);
        getUpcoming();
    }

    public void getUpcoming(){
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(TmdbApi.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TmdbApi tmdbApi = retrofit.create(TmdbApi.class);
        Call<Movies> call = tmdbApi.getUpcoming(TmdbApi.API_KEY);
        call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                if(response.isSuccessful()){
                    Movies movies = response.body();
                    showUpcomingList(movies);
                }
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {

            }
        });
    }

    public void showUpcomingList(Movies movies){
        MoviesAdapter moviesAdapter = new MoviesAdapter(movies, this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rc_movies);
        recyclerView.setLayoutManager(new LinearLayoutManager
                (this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(moviesAdapter);
    }
}
