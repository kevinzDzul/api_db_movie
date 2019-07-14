package com.example.testrestapi.ui.activity.mainView.contract;
import com.example.testrestapi.basePresenter.BasePresenter;
import com.example.testrestapi.basePresenter.BaseView;
import com.example.testrestapi.model.Category;
import java.util.List;

public interface MainContract {
     interface View extends BaseView<Presenter> {
        void loadListCategories(List<Category>categories);
    }

     interface Presenter extends BasePresenter {
        void getListCategories(int page);
    }
}
