package com.example.testrestapi.model;


import java.io.Serializable;
import java.util.List;

@SuppressWarnings("ALL")
public class Category implements Serializable {


    private String title;
    private List<Movie> movieList;
    private String keyCategory;

    public Category(String title, List<Movie> movieList, String keyCategory) {
        this.title = title;
        this.movieList = movieList;
        this.keyCategory = keyCategory;
    }

    public String getKeyCategory() {
        return keyCategory;
    }

    public void setKeyCategory(String keyCategory) {
        this.keyCategory = keyCategory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }
}