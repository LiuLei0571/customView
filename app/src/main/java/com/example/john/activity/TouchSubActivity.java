package com.example.john.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;

import com.example.john.customviewtitle.R;
import com.example.join.util.ButtonSub;
import com.example.join.util.LinearLayoutSub;

/**
 * 用途：
 * 作者：Created by john on 2017/8/2.
 * 邮箱：liulei2@aixuedai.com
 */


public class TouchSubActivity extends Activity {
    private String TAG = "TouchSubActivity";
    private LinearLayoutSub mLytSub;
    private ButtonSub mBtnSub;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_sub);
        mLytSub = (LinearLayoutSub) findViewById(R.id.lyt_sub);
        mBtnSub = (ButtonSub) findViewById(R.id.btn_sub);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, TAG + "dispatchTouch--->action_down");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, TAG + "dispatchTouch--->action_up");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, TAG + "dispatchTouch--->action_move");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, TAG + "onTouchEvent--->action_down");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, TAG + "onTouchEvent--->action_up");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, TAG + "onTouchEvent--->action_move");
                break;
        }
        return super.onTouchEvent(event);
    }
}
