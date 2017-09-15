package com.example.john.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.john.customviewtitle.R;
import com.example.join.util.CustomDemoView;

/**
 * 用途：
 * 作者：Created by john on 2016/7/5.
 * 邮箱：liulei2@aixuedai.com
 */

public class CustomDemoActivty extends AppCompatActivity {
    private CustomDemoView customDemoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_demo);
        customDemoView = (CustomDemoView) findViewById(R.id.custom_demo);
    }
}
