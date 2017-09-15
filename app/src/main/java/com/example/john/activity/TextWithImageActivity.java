package com.example.john.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.john.customviewtitle.R;
import com.example.join.util.TextWithImageView;

/**
 * 用途：
 * 作者：Created by john on 2017/7/27.
 * 邮箱：liulei2@aixuedai.com
 */


public class TextWithImageActivity extends Activity {
    private TextWithImageView mTiOne;
    private TextWithImageView mTiTwo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_with_image);
        mTiOne = (TextWithImageView) findViewById(R.id.ti_one);
        mTiTwo = (TextWithImageView) findViewById(R.id.ti_two);
        initData();
    }

    private void initData() {
        mTiOne.setNumber(1);
        mTiOne.setTitle("美女");
        mTiOne.setResId(R.drawable.ic_one);
        mTiOne.setContent(R.layout.view_image);
        mTiTwo.setNumber(2);
        mTiTwo.setTitle("输入框");
        mTiTwo.setResId(R.drawable.ic_one);
        mTiTwo.setContent(R.layout.view_text);
    }
}
