package com.example.paggerapp.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.example.paggerapp.databinding.SingleMovieItemBinding;
import com.example.paggerapp.model.Movie;

import kotlinx.coroutines.CoroutineDispatcher;

public class MoviesAdapter extends PagingDataAdapter<Movie, MoviesAdapter.MoviesViewHolder> {

    public static final int LOADING_ITEM = 0;
    public static final int MOVIE_ITEM = 1;
    RequestManager glide;

    public MoviesAdapter(@NonNull DiffUtil.ItemCallback<Movie> diffCallback,RequestManager glide) {
        super(diffCallback);
        this.glide = glide;
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MoviesViewHolder(SingleMovieItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        return position == getItemCount()  ? MOVIE_ITEM : LOADING_ITEM;
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
        Movie currentmovie = getItem(position);
        if (currentmovie != null) {
            glide.load("https://image.tmdb.org/t/p/w500/"+currentmovie.getPoster_path())
            .into(holder.movieItemBinding.imageViewMovie);
            holder.movieItemBinding.textViewRating.setText(String.valueOf(currentmovie.getVote_average()));
        }


    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder{
        SingleMovieItemBinding movieItemBinding;
        public MoviesViewHolder(@NonNull SingleMovieItemBinding movieItemBinding) {
         super(movieItemBinding.getRoot());
         this.movieItemBinding = movieItemBinding;
        }
    }
}
