package me.nutchy.cine;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.nutchy.cine.Adapter.MoviesAdapter;
import me.nutchy.cine.Api.TmdbApi;
import me.nutchy.cine.Model.Movie;
import me.nutchy.cine.Model.Movies;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainFragment extends Fragment implements MoviesAdapter.MoviesAdapterListener{


    public MainFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        getUpcoming(view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void getUpcoming(final View view) {
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
                    showUpcomingList(movies, view);
                }
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {

            }
        });
    }

    public void showUpcomingList(Movies movies, View view){
        MoviesAdapter moviesAdapter = new MoviesAdapter(movies, getActivity());
//        moviesAdapter.setMoviesAdapterListener(getActivity());
        RecyclerView recyclerView = view.findViewById(R.id.rc_upcoming_movies);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(moviesAdapter);
    }

    @Override
    public void onItemClickListener(Movie movie) {
        Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
        intent.putExtra("movie", movie);
        startActivity(intent);
    }
}
