package com.example.join.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 用途：
 * 作者：Created by john on 2016/7/5.
 * 邮箱：liulei2@aixuedai.com
 */

public class CustomDemoView extends View {
    private Paint paint=new Paint();
    public CustomDemoView(Context context) {
        super( context,null);
    }

    public CustomDemoView(Context context, AttributeSet attrs) {
        super(context, attrs,0);
    }

    public CustomDemoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
        super.onDraw(canvas);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
//        canvas.drawLine(0,0,100,100,paint);
//        canvas.drawCircle(200,200,40,paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(20);
//        canvas.drawCircle(200,200,40,paint);
        canvas.drawRect(200,200,600,600,paint);
        canvas.drawRoundRect(100,100,150,150,20,20,paint);
        canvas.drawOval(100,400,600,600,paint);
        RectF rectF=new RectF(100,100,200,300);
        paint.setColor(Color.YELLOW);
        canvas.drawArc(rectF,0,270,true,paint);
    }
}
