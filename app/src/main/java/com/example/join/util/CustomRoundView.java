package com.example.join.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.example.john.customviewtitle.R;

/**
 * 用途：
 * 作者：Created by john on 2017/3/16.
 * 邮箱：liulei2@aixuedai.com
 */


public class CustomRoundView extends View {
    Paint mPaint;

    public CustomRoundView(Context context) {
        super(context);
    }

    public CustomRoundView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mPaint == null) {
            mPaint = new Paint();
        }
        mPaint.setAntiAlias(true);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.meinv);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int radius = Math.min(width, height)/2;
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        mPaint.setShader(bitmapShader);
        canvas.drawCircle(radius, radius, radius, mPaint);
    }
}
