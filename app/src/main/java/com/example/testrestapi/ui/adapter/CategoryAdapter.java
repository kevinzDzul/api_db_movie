package com.example.testrestapi.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.testrestapi.R;
import com.example.testrestapi.model.Category;
import com.example.testrestapi.model.Movie;
import com.example.testrestapi.ui.viewHolder.CategoryMovieViewHolder;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryMovieViewHolder> {

    private final List<Category> categoryList;
    private final Context context;

    public CategoryAdapter(List<Category> categories,Context mContext) {
        this.categoryList = categories;
        context = mContext;
    }

    @Override
    public CategoryMovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout._list_tem_movies, parent, false);
        return new CategoryMovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryMovieViewHolder holder, int position) {
        List<Movie> movieList = categoryList.get(position).getMovieList();
        String nameCategory = categoryList.get(position).getTitle();
        String category = categoryList.get(position).getKeyCategory();
        holder.bind(movieList,nameCategory,category,context);
    }

    @Override
    public int getItemCount() {
        return this.categoryList.size();
    }

    @Override
    public void onViewRecycled(CategoryMovieViewHolder holder) {
        super.onViewRecycled(holder);
    }
}
