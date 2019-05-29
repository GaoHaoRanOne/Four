package com.example.myapplication.model;

import com.example.myapplication.api.MyService;
import com.example.myapplication.bean.HomeItemBean;
import com.example.myapplication.callback.HomeCallBack;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainHomeModelImpl implements MainHomeModel{
    @Override
    public void getData(final HomeCallBack callBack) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(MyService.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MyService myService = retrofit.create(MyService.class);
        Observable<HomeItemBean> data = myService.HomeGetData();
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeItemBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeItemBean homeItemBean) {
                        callBack.onHomeSuccess(homeItemBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onHomeFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
