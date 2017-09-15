package com.example.join.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 用途：
 * 作者：Created by john on 2016/7/20.
 * 邮箱：liulei2@aixuedai.com
 */

public class CustomTaijiView extends View {

    private Paint whitePaint, blackPaint;
    private float desAngle;

    public void setDesAngle(float desAngle) {
        this.desAngle = desAngle;
        invalidate();
    }

    public CustomTaijiView(Context context) {
        super(context, null);
        initPaint();
    }

    public CustomTaijiView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        initPaint();
    }

    public CustomTaijiView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    public void initPaint() {
        whitePaint = new Paint();
        whitePaint.setColor(Color.WHITE);
        whitePaint.setAntiAlias(true);
        blackPaint = new Paint();
        blackPaint.setAntiAlias(true);
        blackPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int heght = getHeight();
        int radius = Math.min(width, heght) / 2-100;
        Point centerPoint = new Point(width / 2, heght / 2);
        canvas.translate(centerPoint.x, centerPoint.y);
        canvas.drawColor(Color.GRAY);
        canvas.rotate(desAngle);
        RectF rectF = new RectF(-radius, -radius, radius, radius);
        canvas.drawArc(rectF, 90, 180, true, blackPaint);
        canvas.drawArc(rectF, -90, 180, true, whitePaint);
        canvas.drawCircle(0, -radius / 2, radius / 2, blackPaint);
        canvas.drawCircle(0, -radius / 2, radius / 6, whitePaint);
        canvas.drawCircle(0, radius / 2, radius / 2, whitePaint);
        canvas.drawCircle(0, radius / 2, radius / 6, blackPaint);

    }
}
