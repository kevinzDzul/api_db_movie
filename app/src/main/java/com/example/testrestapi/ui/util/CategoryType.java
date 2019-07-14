package com.example.testrestapi.ui.util;

import com.example.testrestapi.BuildConfig;
import com.example.testrestapi.model.MoviePageResult;
import com.example.testrestapi.network.GetMovieDataService;
import com.example.testrestapi.network.RetrofitInstance;

import io.reactivex.Observable;

public enum CategoryType {
    UPCOMING {
        @Override
        public Observable<MoviePageResult>  getMovieByCategory(int page){
            GetMovieDataService movieDataService = RetrofitInstance.getRetrofitInstance().create(GetMovieDataService.class);
            return movieDataService.getPopularMovies(page, BuildConfig.ApiKey);
        }
    },
    NOWPLAYING {
        @Override
        public Observable<MoviePageResult>  getMovieByCategory(int page){
            GetMovieDataService movieDataService = RetrofitInstance.getRetrofitInstance().create(GetMovieDataService.class);
            return movieDataService.getNowPlayingMovies(page, BuildConfig.ApiKey);
        }
    },
    POPULAR {
        @Override
        public Observable<MoviePageResult>  getMovieByCategory(int page){
            GetMovieDataService movieDataService = RetrofitInstance.getRetrofitInstance().create(GetMovieDataService.class);
            return movieDataService.getPopularMovies(page, BuildConfig.ApiKey);
        }
    };

    public abstract Observable<MoviePageResult>  getMovieByCategory(int page);
}
