package com.example.testrestapi.ui.activity.mainView;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.testrestapi.R;
import com.example.testrestapi.model.Movie;
import com.example.testrestapi.model.MovieDetail;
import com.example.testrestapi.ui.activity.mainView.contract.DetailMovieContract;
import com.example.testrestapi.ui.activity.mainView.presenter.DetailMoviePresenter;
import com.example.testrestapi.ui.util.Const;
import com.example.testrestapi.ui.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailMovieActiviry extends AppCompatActivity implements DetailMovieContract.View {

    DetailMovieContract.Presenter mpresenter;

    @BindView(R.id.postermovie)
    ImageView poster;

    @BindView(R.id.titleposter)
    TextView title;

    @BindView(R.id.date)
    TextView date;

    @BindView(R.id.overview)
    TextView overview;


    @BindView(R.id.votes)
    TextView votes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie_activiry);
        Intent intent = getIntent();
        int idMovie = intent.getIntExtra(Const.IDMOVIE,0);
        mpresenter = new DetailMoviePresenter(this);
        mpresenter.getDetailMovie(idMovie);
        ButterKnife.bind(this);
    }


    @Override
    public void loadDetailMovie(MovieDetail movie) {


        Glide.with(this).load(Utils.movieImagePathBuilder(movie.getPosterPath())).into(poster);
         title.setText(movie.getTitle().concat(" - original title ").concat(movie.getOriginalTitle()));
         date.setText(movie.getReleaseDate());
         overview.setText(movie.getOverview());
         votes.setText("votes of aprovation " + movie.getVoteCount());


    }

    @Override
    public void loadTrailerMovie() {

    }

    @Override
    public void setPresenter(DetailMovieContract.Presenter presenter) {
        mpresenter = presenter;
    }

    @Override
    public Activity getViewActivity() {
        return this;
    }
}
