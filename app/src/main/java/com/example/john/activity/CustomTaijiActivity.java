package com.example.john.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;

import com.example.john.customviewtitle.R;
import com.example.join.util.CustomTaijiView;

/**
 * 用途：
 * 作者：Created by john on 2016/7/20.
 * 邮箱：liulei2@aixuedai.com
 */

public class CustomTaijiActivity extends Activity {
    private CustomTaijiView customTaijiView;
    private float add = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taiji);
        customTaijiView = (CustomTaijiView) findViewById(R.id.taiji);
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                customTaijiView.setDesAngle(add += 2
                );
                this.sendEmptyMessageDelayed(0, 20);
            }

        };
        handler.sendEmptyMessageDelayed(0, 20);
    }

}
