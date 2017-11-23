package me.nutchy.cine.Adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import me.nutchy.cine.Model.Movie;
import me.nutchy.cine.Model.Movies;
import me.nutchy.cine.R;

/**
 * Created by nutchy on 19/11/2017 AD.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesHolder> {

    private Movies movies;
    private Context context;

    private MoviesAdapterListener moviesAdapterListener;
    public interface MoviesAdapterListener {
        void onItemClickListener(Movie movie);
    }

    public MoviesAdapterListener getMoviesAdapterListener() {
        return moviesAdapterListener;
    }

    public void setMoviesAdapterListener(MoviesAdapterListener moviesAdapterListener) {
        this.moviesAdapterListener = moviesAdapterListener;
    }

    public MoviesAdapter(Movies movies, Context context) {
        this.movies = movies;
        this.context = context;
    }

    @Override
    public MoviesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.movie_item, parent, false);
        return new MoviesHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MoviesHolder holder, int position) {
        final Movie movie = movies.getResults().get(position);
        ImageView iV_poster = holder.getiV_poster();
        Glide.with(context)
                .load(movie.BASE_URL_POSTER + movie.getPoster_path()).into(iV_poster);
        holder.iV_poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moviesAdapterListener.onItemClickListener(movie);
            }
        });
        TextView movieName = holder.tv_MovieName;
        movieName.setText(movie.getTitle());

    }

    @Override
    public int getItemCount() {
        return movies.getResults().size();
    }

    public class MoviesHolder extends RecyclerView.ViewHolder {

        private ImageView iV_poster;
        private TextView tv_MovieName;

        public MoviesHolder(View itemView) {
            super(itemView);
            iV_poster = itemView.findViewById(R.id.iV_poster);
            tv_MovieName = itemView.findViewById(R.id.tv_movieName);
        }

        public ImageView getiV_poster() {
            return iV_poster;
        }

        public void setiV_poster(ImageView iV_poster) {
            this.iV_poster = iV_poster;
        }

        public void setTv_MovieName(TextView tv_MovieName) {
            this.tv_MovieName = tv_MovieName;
        }
    }
}
