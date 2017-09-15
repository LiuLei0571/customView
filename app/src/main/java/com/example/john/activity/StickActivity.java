package com.example.john.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.john.customviewtitle.R;
import com.example.join.fragment.MyFragment;
import com.example.join.util.TabLytView;

/**
 * 用途：
 * 作者：Created by john on 2016/8/15.
 * 邮箱：liulei2@aixuedai.com
 */

public class StickActivity extends AppCompatActivity {
    private TabLytView tabLytView;
    private String[] title = new String[]{"简介", "相关", "评论"};
    private Fragment[] fragments = new Fragment[3];
    private ViewPager viewPager;
    private FragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stick);
        initView();
    }

    public void initView() {
        tabLytView = (TabLytView) findViewById(R.id.tablytview);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        tabLytView.setTitle(title);
        for (int i = 0; i < 3; i++) {
            fragments[i] = MyFragment.newInstance(title[i]);
        }
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }

            @Override
            public int getCount() {
                return fragments.length;
            }
        };
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                tabLytView.setLineChange(position, positionOffset);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setAdapter(mAdapter);
        viewPager.setCurrentItem(0);
    }
}
