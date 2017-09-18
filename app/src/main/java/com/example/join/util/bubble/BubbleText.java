package com.example.join.util.bubble;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
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
    BubbleDrawabe mDrawabe;
    RectF mRecf;
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

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
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);
        setUp();
    }

    private void setUp() {
        setUp(getWidth(), getHeight());
    }

    private void setUp(int width, int height) {
        setUp(0, width, 0, height);
    }

    private void setUp(int left, int right, int top, int bottom) {
        mRecf = new RectF(left, top, right, bottom);
        mDrawabe = new BubbleDrawabe();
        mDrawabe.setRectf(mRecf);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mDrawabe != null) {
            mDrawabe.draw(canvas);
        }
        super.onDraw(canvas);
    }
}
