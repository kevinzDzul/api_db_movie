package com.example.testrestapi.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.testrestapi.R;
import com.example.testrestapi.model.Movie;
import com.example.testrestapi.ui.viewHolder.MoviesHolder;
import com.example.testrestapi.ui.util.MovieClickListener;

import java.util.List;

@SuppressWarnings("ALL")
public class MoviesAdapter extends RecyclerView.Adapter<MoviesHolder> {

    private final MovieClickListener movieClickListener;
    private final List<Movie> movieList;

    public MoviesAdapter(List<Movie> movieList, MovieClickListener movieClickListener) {
        this.movieList = movieList;
        this.movieClickListener = movieClickListener;
    }

    @Override
    public MoviesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout._card_movie, parent, false);
        return new MoviesHolder(view);
    }

    @Override
    public void onBindViewHolder(MoviesHolder holder, int position) {
        Movie movie = this.movieList.get(position);
        holder.bind(movie, movieClickListener);
    }

    @Override
    public int getItemCount() {
        return this.movieList.size();
    }

    @Override
    public void onViewRecycled(MoviesHolder holder) {
        super.onViewRecycled(holder);
    }
}
