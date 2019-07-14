package com.example.testrestapi.ui.activity.mainView.contract;

import com.example.testrestapi.basePresenter.BasePresenter;
import com.example.testrestapi.basePresenter.BaseView;
import com.example.testrestapi.model.Movie;

import java.util.List;

public interface MoviesContract {
    interface View extends BaseView<Presenter> {
        void loadListMovies(List<Movie> movies, int currentPage, int totalPages);
    }

    interface Presenter extends BasePresenter {
        void getListMovies(String category,int page);
        void launchDetailMovie(int id);
    }
}
