package com.example.myapplication.framgent;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.adapter.MyHomeAdapter;
import com.example.myapplication.bean.HomeItemBean;
import com.example.myapplication.model.MainHomeModelImpl;
import com.example.myapplication.presenter.MainHomePresenterImpl;
import com.example.myapplication.view.MainHomeView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomFragment extends Fragment implements MainHomeView {


    private View view;
    private RecyclerView mRlv;
    private ArrayList<HomeItemBean.ResultBean.DataBean> list;
    private MyHomeAdapter adapter;

    public CustomFragment() {
        // Required empty public constructor
    }

    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_custom, container, false);
        initView(inflate);
        initData();
        return inflate;
    }

    private void initData() {

        MainHomePresenterImpl mainHomePresenter = new MainHomePresenterImpl(new MainHomeModelImpl(), this);
        mainHomePresenter.getData();

    }

    private void initView(View inflate) {
        mRlv = (RecyclerView) inflate.findViewById(R.id.rlv);
        list = new ArrayList<>();
        adapter = new MyHomeAdapter(getContext(), list);
        mRlv.setAdapter(adapter);
        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));
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
