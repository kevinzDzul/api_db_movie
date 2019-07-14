package com.example.testrestapi.ui.viewHolder;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.testrestapi.R;
import com.example.testrestapi.model.Movie;
import com.example.testrestapi.ui.activity.mainView.DetailMovieActiviry;
import com.example.testrestapi.ui.activity.mainView.MoviesActiviy;
import com.example.testrestapi.ui.adapter.MovieAdapter;
import com.example.testrestapi.ui.util.Const;
import com.example.testrestapi.ui.util.EndlessRecyclerViewScrollListener;
import com.example.testrestapi.ui.util.MovieClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryMovieViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.itemTitle)
    TextView categoryView;
    @BindView(R.id.recycler_view_list)
    RecyclerView listMovies;
    @BindView(R.id.btnMore)
    Button btnMore;
    private MovieAdapter movieAdapter;

    public CategoryMovieViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(List<Movie> movies, String nameCategory, final String category, final Context mContext) {

        categoryView.setText(nameCategory);
        listMovies.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        listMovies.setLayoutManager(linearLayoutManager);
        movieAdapter = new MovieAdapter(movies, new MovieClickListener() {
            @Override
            public void onMovieClick(Movie movie) {
                Intent intent = new Intent(mContext, DetailMovieActiviry.class);
                intent.putExtra(Const.IDMOVIE, movie.getId());
                mContext.startActivity(intent);
            }
        });
        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchMoreMovies(mContext, category);
            }
        });
        listMovies.setAdapter(movieAdapter);
    }


    private void launchMoreMovies(Context context,String category){
        Intent intent = new Intent(context, MoviesActiviy.class);
        intent.putExtra(Const.CATEGORYKEY, category);
        context.startActivity(intent);
    }
}
