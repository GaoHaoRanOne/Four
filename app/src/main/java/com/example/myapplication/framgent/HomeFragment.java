package com.example.myapplication.framgent;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.adapter.MyHomeAdapter;
import com.example.myapplication.bean.HomeItemBean;
import com.example.myapplication.model.MainHomeModelImpl;
import com.example.myapplication.presenter.MainHomePresenterImpl;
import com.example.myapplication.view.MainHomeView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements MainHomeView {


    private View view;
    private Banner mBanner;
    private RecyclerView mRlv;
    private ArrayList<String> strings;
    private SearchView mSv;
    private ArrayList<HomeItemBean.ResultBean.DataBean> list;
    private MyHomeAdapter adapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
        initView(inflate);
        initData();
        return inflate;
    }

    private void initData() {


        MainHomePresenterImpl mainHomePresenter = new MainHomePresenterImpl(new MainHomeModelImpl(), this);
        mainHomePresenter.getData();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        ((ViewGroup) mView.getParent()).removeView(mView);
    }

    private void initView(View inflate) {
        mBanner = (Banner) inflate.findViewById(R.id.banner);
        mRlv = (RecyclerView) inflate.findViewById(R.id.rlv);
        list = new ArrayList<>();
        adapter = new MyHomeAdapter(getContext(), list);
        mRlv.setAdapter(adapter);
        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        //放图片地址的集合
        strings = new ArrayList<>();
        strings.add("http://ww1.sinaimg.cn/large/0065oQSqly1g2pquqlp0nj30n00yiq8u.jpg");
        strings.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
        strings.add("https://ww1.sinaimg.cn/large/0065oQSqly1g2hekfwnd7j30sg0x4djy.jpg");
        strings.add("https://ws1.sinaimg.cn/large/0065oQSqly1g0ajj4h6ndj30sg11xdmj.jpg");
        //设置内置样式，共有六种可以点入方法内逐一体验使用。
//        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器，图片加载器在下方
        mBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load((String) path).into(imageView);
            }
        });
        //设置图片网址或地址的集合
        mBanner.setImages(strings);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
//        mBanner.setBannerAnimation(Transformer.Default);
        //设置轮播图的标题集合
//        mBanner.setBannerTitles(list_title);
        //设置轮播间隔时间
        mBanner.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”。
        mBanner.isAutoPlay(true);
//        //设置指示器的位置，小点点，左中右。
//        mBanner.setIndicatorGravity(BannerConfig.CENTER)
//                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
//                .setOnBannerListener(new OnBannerListener() {
//                    @Override
//                    public void OnBannerClick(int position) {
//                        Log.d("tag", "你点了第"+position+"张轮播图");
//                    }
//                })
//                //必须最后调用的方法，启动轮播图。
        mBanner.start();
        mSv = (SearchView) inflate.findViewById(R.id.sv);
        mSv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (!TextUtils.isEmpty(s)){

                }
                return false;
            }
        });

    }

    @Override
    public void onHomeSuccess(HomeItemBean homeItemBean) {
        list.addAll(homeItemBean.getResult().getData());
        adapter.setList(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onHomeFail(String str) {

    }
}
