package com.example.testrestapi.network;


import com.example.testrestapi.model.MovieDetail;
import com.example.testrestapi.model.MoviePageResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

@SuppressWarnings("ALL")
public interface GetMovieDataService {


    @GET("movie/{movie_id}")
    Observable<MovieDetail>getDetailMovie(@Path("movie_id") int movie_id, @Query("api_key") String userkey);

    @GET("movie/upcoming")
    Observable<MoviePageResult> getPopularMovies(@Query("page") int page, @Query("api_key") String userkey);

    @GET("movie/now_playing")//in theater movie
    Observable<MoviePageResult> getNowPlayingMovies(@Query("page") int page, @Query("api_key") String userkey);

    @GET("movie/popular")//populars
    Observable<MoviePageResult> getTopPopularMovies(@Query("page") int page, @Query("api_key") String userkey);

}