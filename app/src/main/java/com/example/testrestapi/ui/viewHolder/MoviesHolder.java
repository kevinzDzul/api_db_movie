package com.example.testrestapi.ui.viewHolder;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.testrestapi.R;
import com.example.testrestapi.model.Movie;
import com.example.testrestapi.ui.util.MovieClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressWarnings("ALL")
public class MoviesHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.thumbnail_movie)
    ImageView mMoviePoster;
    @BindView(R.id.rating)
    TextView rating;

    @BindView(R.id.title_movie)
    TextView mMovieCard;

    public MoviesHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final Movie movie, final MovieClickListener movieClickListener) {


        Glide.with(itemView).load(movieImagePathBuilder(movie.getPosterPath())).into(mMoviePoster);
        mMovieCard.setText(movie.getTitle());
        rating.setText(" Votes of peoples : "+ movie.getVoteCount());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieClickListener.onMovieClick(movie);
            }
        });
    }

    private int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }
    private int getMeasuredPosterHeight(int width) {
        return (int) (width * 1.5f);
    }


    public static String movieImagePathBuilder(String imagePath) {
        return "https://image.tmdb.org/t/p/" +
                "w500" +
                imagePath;
    }
}