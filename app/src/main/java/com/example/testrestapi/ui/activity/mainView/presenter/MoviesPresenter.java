package com.example.testrestapi.ui.activity.mainView.presenter;

import android.content.Intent;

import com.example.testrestapi.model.Movie;
import com.example.testrestapi.model.MoviePageResult;
import com.example.testrestapi.ui.activity.mainView.DetailMovieActiviry;
import com.example.testrestapi.ui.activity.mainView.MoviesActiviy;
import com.example.testrestapi.ui.activity.mainView.contract.MoviesContract;
import com.example.testrestapi.ui.util.CategoryType;
import com.example.testrestapi.ui.util.Const;


import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MoviesPresenter implements MoviesContract.Presenter {

    private MoviesContract.View mview;
    private int currentpage  = 0;
    private List<Movie>movieList;
    private int totalpage = 0;

    public MoviesPresenter(MoviesContract.View mview) {
        this.mview = mview;
        mview.setPresenter(this);
    }

    @Override
    public void getListMovies(String category , final int page) {
        Observable<MoviePageResult> tymovies = CategoryType.valueOf(category).getMovieByCategory(page);
        tymovies.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MoviePageResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(MoviePageResult moviePageResult) {
                        currentpage = moviePageResult.getPage();
                        movieList = moviePageResult.getResults();
                        totalpage = moviePageResult.getTotalPages();
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onComplete() {
                        mview.loadListMovies(movieList,currentpage,totalpage);
                    }
                });
    }

    @Override
    public void launchDetailMovie(int id) {
        Intent intent = new Intent(mview.getViewActivity(), DetailMovieActiviry.class);
        intent.putExtra(Const.IDMOVIE, id);
        mview.getViewActivity().startActivity(intent);
    }

    @Override
    public void start() {
    }
}
