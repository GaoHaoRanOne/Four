package com.example.myapplication.presenter;

import com.example.myapplication.bean.HomeItemBean;
import com.example.myapplication.callback.HomeCallBack;
import com.example.myapplication.model.MainHomeModel;
import com.example.myapplication.view.MainHomeView;

public class MainHomePresenterImpl implements MainHomePresenter, HomeCallBack {
    private MainHomeModel mainHomeModel;
    private MainHomeView mainHomeView;

    public MainHomePresenterImpl(MainHomeModel mainHomeModel, MainHomeView mainHomeView) {
        this.mainHomeModel = mainHomeModel;
        this.mainHomeView = mainHomeView;
    }

    @Override
    public void onHomeSuccess(HomeItemBean homeItemBean) {
        mainHomeView.onHomeSuccess(homeItemBean);
    }

    @Override
    public void onHomeFail(String str) {
        mainHomeView.onHomeFail(str);
    }

    @Override
    public void getData() {
        mainHomeModel.getData(this);
    }
}
