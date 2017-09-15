package com.example.join.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.john.customviewtitle.R;

/**
 * Created by Liu on 2016/3/22.
 * TODO:原型加载进度条
 */
public class CustomRingView extends View {
    private String mTvProgressBar;
    private  int mTittleSize;
    private Rect mRect;
    private int mFirstColor;
    private int mSecondColor;
    private int mCircleWidth;//圆环宽度
    private int mProgress;//进度条
    private int mSpeed;//速度
    private Paint mPaint;
    private   RectF oval;
    private  int centre;
    private  int radius;
    public CustomRingView(Context context) {

       this(context,null);
    }

    public CustomRingView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
        init(context, attrs,0);
    }

    public CustomRingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }
    public void init(Context context, AttributeSet attrs, int defStyleAttr){
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomProgressBar, defStyleAttr, 0);
        int n = a.getIndexCount();

        for (int i = 0; i < n; i++)
        {
            int attr = a.getIndex(i);
            switch (attr)
            {
                case R.styleable.CustomProgressBar_titleText:
                    mTvProgressBar=a.getString(i);
                    break;
                case R.styleable.CustomProgressBar_titleSize:
                    mTittleSize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.CustomProgressBar_firstColor:
                    mFirstColor = a.getColor(attr, Color.GREEN);
                    break;
                case R.styleable.CustomProgressBar_secondColor:
                    mSecondColor = a.getColor(attr, Color.RED);
                    break;
                case R.styleable.CustomProgressBar_circleWidth:
                    mCircleWidth = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.CustomProgressBar_speed:
                    mSpeed = a.getInt(attr, 20);// 默认20
                    break;
            }
        }
        a.recycle();
        mPaint = new Paint();
        mRect=new Rect();
        //获得文本的长宽
        mPaint.getTextBounds(mTvProgressBar, 0, mTvProgressBar.length(), mRect);
        new Thread(){
            @Override
            public void run() {
                 while (mProgress!=360){
                     mProgress++;

                     postInvalidate();
                    try{
                        Thread.sleep(mSpeed);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                 }
            }
        }.start();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        centre=getWidth()/2;//圆心
        radius=centre-mCircleWidth;//半径
        mPaint.setStrokeWidth(mCircleWidth);//设置圆环的宽度
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        oval=new RectF(centre-radius,centre-radius,centre+radius,centre+radius);
        mPaint.setColor(mSecondColor);
        canvas.drawCircle(centre, centre,radius,mPaint);//先画出第一个圆环
        mPaint.setColor(mFirstColor);
//        oval :指定圆弧的外轮廓矩形区域。
//        startAngle: 圆弧起始角度，单位为度。
//        sweepAngle: 圆弧扫过的角度，顺时针方向，单位为度,从右中间开始为零度。
//        useCenter: 如果为True时，在绘制圆弧时将圆心包括在内，通常用来绘制扇形。关键是这个变量，下面将会详细介绍。
//        paint: 绘制圆弧的画板属性，如颜色，是否填充等。
        canvas.drawArc(oval, -90, mProgress, false, mPaint);//根据进度条画圆环
        Paint mTvPaint=new Paint();
        mTvPaint.setTextSize(mTittleSize);
        mTvPaint.setColor(getResources().getColor(R.color.colorBack));
        canvas.drawText((int)(mProgress/3.6)+"%", getWidth() / 2 - mRect.width() / 2, getHeight() / 2 + mRect.height() / 2, mTvPaint);
    }

    @Override
    protected void onAttachedToWindow() {

        super.onAttachedToWindow();
    }
}
