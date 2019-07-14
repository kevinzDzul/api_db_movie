package com.example.testrestapi.ui.activity.mainView;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.testrestapi.R;
import com.example.testrestapi.model.Category;
import com.example.testrestapi.ui.activity.mainView.contract.MainContract;
import com.example.testrestapi.ui.activity.mainView.presenter.MainPresenter;
import com.example.testrestapi.ui.adapter.CategoryAdapter;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @BindView(R.id.rv_movies)
    RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;
    MainContract.Presenter mainPresenter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        mainPresenter = new MainPresenter(this);

        mainPresenter.getListCategories(1);
    }


    @Override
    public void loadListCategories(List<Category>categories) {
        categoryAdapter = new CategoryAdapter(categories,getApplication());
        recyclerView.setAdapter(categoryAdapter);
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mainPresenter = presenter;
        mainPresenter.start();
    }

    @Override
    public Activity getViewActivity() {
        return this;
    }
}