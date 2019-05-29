package com.example.myapplication.view;

import com.example.myapplication.bean.HomeItemBean;

public interface MainHomeView {

    void onHomeSuccess(HomeItemBean homeItemBean);
    void onHomeFail(String str);

}
