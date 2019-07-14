package com.example.testrestapi.network;


import com.example.testrestapi.model.MovieTrailerResult;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetMovieTrailerService {
    @GET("movie/{id}/videos")
    Observable<MovieTrailerResult> getTrailers(@Path("id") int movieId, @Query("api_key") String userkey);
}