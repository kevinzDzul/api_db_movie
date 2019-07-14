package com.example.testrestapi.ui.activity.mainView.contract;

import com.example.testrestapi.basePresenter.BasePresenter;
import com.example.testrestapi.basePresenter.BaseView;
import com.example.testrestapi.model.Movie;
import com.example.testrestapi.model.MovieDetail;


public interface DetailMovieContract {

    interface View extends BaseView<DetailMovieContract.Presenter> {
        void loadDetailMovie(MovieDetail movie);
        void loadTrailerMovie();
    }

    interface Presenter extends BasePresenter {
        void getDetailMovie(int id);
        void getTrailerMovie();
    }
}
