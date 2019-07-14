package com.example.testrestapi.basePresenter;

import android.app.Activity;

public interface BaseView <T>{
    void setPresenter(T presenter);
    Activity getViewActivity();
}

