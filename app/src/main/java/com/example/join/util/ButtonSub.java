package com.example.join.util;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * 用途：
 * 作者：Created by john on 2017/8/2.
 * 邮箱：liulei2@aixuedai.com
 */


public class ButtonSub extends AppCompatButton {
    private String TAG = "ButtonSub";

    public ButtonSub(Context context) {
        super(context);
    }

    public ButtonSub(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ButtonSub(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG,    "dispatchTouchEvent--->action_down");
//                isT = true;
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
//    return false;
    }
}
