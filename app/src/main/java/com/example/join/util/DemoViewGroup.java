package com.example.join.util;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 用途：
 * 作者：Created by john on 2017/7/6.
 * 邮箱：liulei2@aixuedai.com
 */


public class DemoViewGroup extends ViewGroup {
    public DemoViewGroup(Context context) {
        super(context);
    }

    public DemoViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DemoViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int widtModule = MeasureSpec.getMode(widthMeasureSpec);
        int heghtModule = MeasureSpec.getMode(heightMeasureSpec);
        int mWidht = 0;
        int mHeight = 0;
        int count = getChildCount();
//        if (count > 0) {
//            View childView = getChildAt(0);
//            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
//        }
        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();
            mHeight+=childHeight;
        }

        setMeasuredDimension(widthSize, mHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        int left = 0;
        int mHeight = 0;
        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);

            int widthSize = childView.getMeasuredWidth();
            int heightSize = childView.getMeasuredHeight();
            childView.layout(left, mHeight, widthSize, mHeight + heightSize);
            mHeight += heightSize;

        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }
}
