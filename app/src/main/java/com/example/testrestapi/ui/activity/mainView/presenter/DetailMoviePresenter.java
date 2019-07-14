package com.example.testrestapi.ui.activity.mainView.presenter;

import android.util.Log;
import android.widget.ImageView;

import com.example.testrestapi.BuildConfig;
import com.example.testrestapi.R;
import com.example.testrestapi.model.Movie;
import com.example.testrestapi.model.MovieDetail;
import com.example.testrestapi.network.GetMovieDataService;
import com.example.testrestapi.network.RetrofitInstance;
import com.example.testrestapi.ui.activity.mainView.contract.DetailMovieContract;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DetailMoviePresenter implements DetailMovieContract.Presenter {


    private DetailMovieContract.View mView;
    private MovieDetail mMovie;



    public DetailMoviePresenter(DetailMovieContract.View mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void getDetailMovie(int id){


        Log.e("Errr",""+id);
        GetMovieDataService movieDataService = RetrofitInstance.getRetrofitInstance().create(GetMovieDataService.class);
        movieDataService.getDetailMovie(id, BuildConfig.ApiKey).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieDetail>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieDetail movie) {

                        mMovie = movie;
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("Errr",e.getMessage());

                    }

                    @Override
                    public void onComplete() {
                        mView.loadDetailMovie(mMovie);
                    }
                });
    }

    @Override
    public void getTrailerMovie() {

    }

    @Override
    public void start() {

    }
}
