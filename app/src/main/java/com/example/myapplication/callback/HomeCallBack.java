package com.example.myapplication.callback;

import com.example.myapplication.bean.HomeItemBean;

public interface HomeCallBack {

    void onHomeSuccess(HomeItemBean homeItemBean);
    void onHomeFail(String str);


}
