package com.example.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.myapplication.framgent.CustomFragment;
import com.example.myapplication.framgent.HomeFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    private ArrayList<Fragment> fragments;
    private ViewPager mVp;
    /**
     * 首页
     */
    private RadioButton mRbHome;
    /**
     * 定制
     */
    private RadioButton mRbCustom;
    private RadioGroup mRg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        mVp = (ViewPager) findViewById(R.id.vp);
        mRbHome = (RadioButton) findViewById(R.id.rb_home);
        mRbCustom = (RadioButton) findViewById(R.id.rb_custom);
        mRbHome.setOnClickListener(this);
        mRbCustom.setOnClickListener(this);
        mRg = (RadioGroup) findViewById(R.id.rg);


        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new CustomFragment());

        mVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        //添加测划页面切换事件的监听器
        mVp.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        switch (i) {
            case R.id.rb_home:
                mVp.setCurrentItem(1);
                break;
            case R.id.rb_custom:
                mVp.setCurrentItem(0);
                break;
        }
    }

    @Override
    public void finish() {
        super.finish();
        ViewGroup view = (ViewGroup) getWindow().getDecorView();
        view.removeAllViews();
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_home:
                mVp.setCurrentItem(1, true);
                break;
            case R.id.rb_custom:
                mVp.setCurrentItem(0, true);
                break;

            default:
                break;
        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
