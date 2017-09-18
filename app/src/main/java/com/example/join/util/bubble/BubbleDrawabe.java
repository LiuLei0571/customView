package com.example.join.util.bubble;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * 用途：
 * 作者：Created by liulei on 17/9/18.
 * 邮箱：liulei2@aixuedai.com
 */


public class BubbleDrawabe extends Drawable {
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private RectF mRectf;
    private float mArrawWidth = 80;
    private float mArrowHeight = 30;
    private float mArrowPosition = 20;
    private int bubbleColor = Color.BLUE;
    private boolean mArrworCenter;
    private float mRadius = 20;
    private Path mPath = new Path();

    public void setRectf(RectF rectf) {
        mRectf = rectf;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        setUp(canvas);
    }


    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int i) {
        mPaint.setAlpha(i);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    private void setUp(Canvas canvas) {
        mPaint.setColor(bubbleColor);
        if (mArrworCenter) {
            mArrowPosition = (mRectf.right - mRectf.left) / 2 - mArrawWidth / 2;
        }
        mPath.moveTo(mRectf.left, mRectf.top);
        mPath.lineTo(mRectf.left + mArrowPosition, mRectf.top);
        mPath.lineTo(mRectf.left + mArrowPosition + mArrawWidth / 2, mRectf.top + mArrowHeight);
        mPath.lineTo(mRectf.left + mArrowPosition + mArrawWidth, mRectf.top);
        mPath.lineTo(mRectf.right, mRectf.top);
        mPath.lineTo(mRectf.right, mRectf.bottom);
        mPath.lineTo(mRectf.left, mRectf.bottom);
        mPath.lineTo(mRectf.left, mRectf.top);
        mPath.close();
        canvas.drawPath(mPath, mPaint);
    }
}
