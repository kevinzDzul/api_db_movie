package com.example.testrestapi.ui.activity.mainView.presenter;

import android.util.Log;

import com.example.testrestapi.BuildConfig;
import com.example.testrestapi.model.Category;
import com.example.testrestapi.model.Movie;
import com.example.testrestapi.model.MoviePageResult;
import com.example.testrestapi.network.GetMovieDataService;
import com.example.testrestapi.network.RetrofitInstance;
import com.example.testrestapi.ui.activity.mainView.contract.MainContract;
import com.example.testrestapi.ui.util.Const;

import java.util.ArrayList;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements MainContract.Presenter {


    private MainContract.View mView;
    private List<Category> categories;

    public MainPresenter(MainContract.View view) {
        mView = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getListCategories(int page) {

        GetMovieDataService movieDataService = RetrofitInstance.getRetrofitInstance().create(GetMovieDataService.class);
        final Observable<MoviePageResult> upComingObservable
                = movieDataService.getPopularMovies(page, BuildConfig.ApiKey).subscribeOn(Schedulers.io()).onErrorReturn(new Function<Throwable, MoviePageResult>() {
            @Override
            public MoviePageResult apply(Throwable error) throws Exception {
                return  new MoviePageResult(new ArrayList<Movie>(0));
            }
        });
        Observable<MoviePageResult> nowPlayingObservable
                = movieDataService.getNowPlayingMovies(page, BuildConfig.ApiKey).subscribeOn(Schedulers.io()).onErrorReturn(new Function<Throwable, MoviePageResult>() {
            @Override
            public MoviePageResult apply(Throwable error) throws Exception {
                return  new MoviePageResult(new ArrayList<Movie>(0));
            }
        });

        Observable<MoviePageResult> popularObservable
                = movieDataService.getTopPopularMovies(page, BuildConfig.ApiKey).subscribeOn(Schedulers.io()).onErrorReturn(new Function<Throwable, MoviePageResult>() {
            @Override
            public MoviePageResult apply(Throwable error) throws Exception {
                return  new MoviePageResult(new ArrayList<Movie>(0));
            }
        });

        Observable.zip(upComingObservable, nowPlayingObservable, popularObservable, new Function3<MoviePageResult, MoviePageResult, MoviePageResult, List<Category>>() {
            @Override
            public List<Category> apply(MoviePageResult moviePageResult, MoviePageResult moviePageResult2, MoviePageResult moviePageResult3) throws Exception {

                List<Category> listCategoryWithData = new ArrayList<>();

                listCategoryWithData.add(new Category("Up Coming Movie",moviePageResult.getResults(), Const.UPCOMING));
                listCategoryWithData.add(new Category("Now Playing Moview",moviePageResult2.getResults(),Const.NOWPLAYING));
                listCategoryWithData.add(new Category("Popular Movie",moviePageResult3.getResults(),Const.POPULAR));

                return listCategoryWithData;
            }
        }) .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<Category>>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(List<Category> categoryList) {
                categories = new ArrayList<>(categoryList);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Err",e.getMessage());
            }

            @Override
            public void onComplete() {


                mView.loadListCategories(categories);


            }
        });


    }


}
