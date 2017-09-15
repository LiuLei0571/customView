package com.example.join.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 用途：
 * 作者：Created by john on 2016/8/16.
 * 邮箱：liulei2@aixuedai.com
 */

public class TabLytView extends LinearLayout {
    private String[] tilte;
    private int mTabCount;
    private Paint paint = new Paint();
    private float mTotalX = 0;

    public TabLytView(Context context) {
        super(context);
    }

    public TabLytView(Context context, AttributeSet attrs) {
        super(context, attrs);


    }

    public TabLytView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(9.0F);
        paint.setAntiAlias(true);
        // canvas.translate(mTotalX,getHeight());
        canvas.drawLine(mTotalX, getHeight(), mTotalX + getWidth() / 3, getHeight(), paint);

    }

    public void setTitle(String[] title) {
        this.tilte = title;
        this.mTabCount = title.length;
        getView();
    }

    public void getView() {
        for (int i = 0; i < mTabCount; i++) {
            TextView tv = new TextView(getContext());
            tv.setText(tilte[i]);
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(14);
            LayoutParams lp = new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.weight = 1;
            lp.gravity = Gravity.CENTER;
            tv.setLayoutParams(lp);
            addView(tv);

        }
    }

    public void setLineChange(int position, float offset) {
        mTotalX = getWidth() / mTabCount * (position + offset);
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }
}
