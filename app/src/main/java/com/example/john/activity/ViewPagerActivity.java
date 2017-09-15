package com.example.john.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.john.customviewtitle.R;

/**
 * Created by john on 2016/5/10.
 */
public class ViewPagerActivity extends Activity {
    private ViewPager mViewPager;
    private PagerAdapter mAdater;
    private int[] ImgRes = {R.drawable.a, R.drawable.b, R.drawable.c};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setPageMargin(23);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mAdater = new PagerAdapter() {
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView img = new ImageView(ViewPagerActivity.this);
                img.setImageResource(ImgRes[position]);
                container.addView(img);
                return img;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public int getCount() {
                return ImgRes.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        });
    }
}
