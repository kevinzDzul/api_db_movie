package com.example.testrestapi.ui.activity.mainView;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.testrestapi.R;
import com.example.testrestapi.model.Movie;
import com.example.testrestapi.ui.activity.mainView.contract.MoviesContract;
import com.example.testrestapi.ui.activity.mainView.presenter.MoviesPresenter;
import com.example.testrestapi.ui.adapter.MoviesAdapter;
import com.example.testrestapi.ui.util.Const;
import com.example.testrestapi.ui.util.EndlessRecyclerViewScrollListener;
import com.example.testrestapi.ui.util.MovieClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesActiviy extends AppCompatActivity implements MoviesContract.View {

    @BindView(R.id.db_movies)
    RecyclerView recyclerViewMovies;
    private MoviesContract.Presenter mPresenter;
    private static int totalPages;
    private MoviesAdapter movieAdapter;
    private List<Movie> movieResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_activiy);
        mPresenter = new MoviesPresenter(this);
        ButterKnife.bind(this);


        Intent intent = getIntent();
        final String category = intent.getStringExtra(Const.CATEGORYKEY);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL, false);
        recyclerViewMovies.setLayoutManager(linearLayoutManager);
        mPresenter.getListMovies(category,1);
        EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if ((page + 1) <= totalPages) {
                    mPresenter.getListMovies(category,page + 1);
                }
            }
        };
        recyclerViewMovies.addOnScrollListener(scrollListener);
    }


    @Override
    public void loadListMovies(List<Movie> movies, int currentPage, int totalPages) {

        if(currentPage == 1) {
             this.totalPages = totalPages;
            movieResults = new ArrayList<>(movies);
            movieAdapter = new MoviesAdapter(movieResults, new MovieClickListener() {
                @Override
                public void onMovieClick(Movie movie) {
                    mPresenter.launchDetailMovie(movie.getId());
                }
            });
            recyclerViewMovies.setAdapter(movieAdapter);
        } else {
            for(Movie movie : movies){
                movieResults.add(movie);
                movieAdapter.notifyItemInserted(movieResults.size() - 1);
            }
        }

    }

    @Override
    public void setPresenter(MoviesContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public Activity getViewActivity() {
        return this;
    }
}
