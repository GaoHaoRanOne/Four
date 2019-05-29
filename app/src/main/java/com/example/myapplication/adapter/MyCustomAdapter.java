package com.example.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.bean.HomeItemBean;

import java.util.ArrayList;

public class MyCustomAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<HomeItemBean.ResultBean.DataBean>list;

    public void setList(ArrayList<HomeItemBean.ResultBean.DataBean> list) {
        this.list = list;
    }

    public MyCustomAdapter(Context context, ArrayList<HomeItemBean.ResultBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_custom_item, viewGroup, false);
        return new Holder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Holder holder = (Holder) viewHolder;
        HomeItemBean.ResultBean.DataBean dataBean = list.get(i);
        Glide.with(context).load(dataBean.getThumbnail_pic_s()).into(holder.iv_thumbnail_pic_s);
        holder.tv_title.setText(dataBean.getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class Holder extends RecyclerView.ViewHolder {

        private final ImageView iv_thumbnail_pic_s;
        private final TextView tv_title;

        public Holder(@NonNull View itemView) {
            super(itemView);
            iv_thumbnail_pic_s = itemView.findViewById(R.id.iv_thumbnail_pic_s);
            tv_title = itemView.findViewById(R.id.tv_title);
        }
    }
}
