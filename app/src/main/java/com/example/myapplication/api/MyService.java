package com.example.myapplication.api;

import com.example.myapplication.bean.HomeItemBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MyService {

    String url = "Http:192.168.1.104:8080/demo/";
    @GET("txt.txt")
    Observable<HomeItemBean> HomeGetData();

}
