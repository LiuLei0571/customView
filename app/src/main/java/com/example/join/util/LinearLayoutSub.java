package com.example.join.util;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;


/**
 * 用途：
 * 作者：Created by john on 2017/8/2.
 * 邮箱：liulei2@aixuedai.com
 */


public class LinearLayoutSub extends LinearLayout {
    private String TAG = "LinearLayoutSub";

    public LinearLayoutSub(Context context) {
        super(context);
    }

    public LinearLayoutSub(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LinearLayoutSub(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG,    "dispatchTouchEvent--->action_down");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG,    "dispatchTouchEvent--->action_up");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG,    "dispatchTouchEvent--->action_move");
                break;
        }
        return super.dispatchTouchEvent(event);
    }

//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                Log.d(TAG, TAG + "onTouchEvent--->action_down");
//                break;
//            case MotionEvent.ACTION_UP:
//                Log.d(TAG, TAG + "onTouchEvent--->action_up");
//                break;
//            case MotionEvent.ACTION_MOVE:
//                Log.d(TAG, TAG + "onTouchEvent--->action_move");
//                break;
//        }
//        return true;
//    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
                switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG,    "onTouchEvent--->action_down");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG,    "onTouchEvent--->action_up");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG,    "onTouchEvent--->action_move");
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG,    "onInterceptTouchEvent--->action_down");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG,    "onInterceptTouchEvent--->action_up");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG,    "onInterceptTouchEvent--->action_move");
                break;
        }
        return super.onInterceptTouchEvent(event);
    }
}
