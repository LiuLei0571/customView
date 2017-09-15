package com.example.join.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * 用途：
 * 作者：Created by john on 2017/9/15.
 * 邮箱：liulei2@aixuedai.com
 */


public class BubbleText extends AppCompatTextView {
    Context mContext;
    Drawable mDrawabe;
    public BubbleText(Context context) {
        super(context, null);
    }

    public BubbleText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public BubbleText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    public void initView(Context context, AttributeSet attrs) {
        mContext = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mDrawabe != null) {
            mDrawabe.draw(canvas);
        }
        super.onDraw(canvas);
    }
}
