package com.example.join.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.example.john.customviewtitle.R;

/**
 * Created by Liu on 2016/3/23.
 * TODO:
 */
public class CustomSoundView extends View {
    private int mFirstColor;
    private int mSecondColor;
    private int mSplitSize;//每块图片的间隙
    private int mCount;
    private int mCurrentCount=3;//当前进度
    private int mCircleWidth;//圆的宽度
    private Bitmap mImage;
    private Rect mRect;
    private Paint mPaint;
    private int xDown;
    private int xUp;
    public CustomSoundView(Context context) {
        this(context,null);
    }

        //AttributeSet可以获取布局文件的所定义控件的属性所有的key与value
    public CustomSoundView(Context context, AttributeSet attrs) {

        super(context, attrs);
        init(context, attrs, 0);
    }

    public CustomSoundView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }
    public void init(Context context, AttributeSet attrs, int defStyleAttr){
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomSound, defStyleAttr, 0);
        int n = a.getIndexCount();
        for(int i=0;i<n;i++){
            int attr=a.getIndex(i);
            switch (attr){
                case R.styleable.CustomSound_bg:
                    mImage= BitmapFactory.decodeResource(getResources(), a.getResourceId(attr, 0));
                    break;
                case R.styleable.CustomSound_circleWidth:
                    mCircleWidth = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.CustomSound_dotCount:
                    mCount=a.getInt(attr, 20);
                    break;
                case R.styleable.CustomSound_firstColor:
                    mFirstColor = a.getColor(attr, Color.GREEN);
                    break;
                case R.styleable.CustomSound_secondColor:
                    mSecondColor = a.getColor(attr, Color.RED);
                    break;
                case R.styleable.CustomSound_splitSize:
                    mSplitSize=a.getInt(attr,20);
                    break;
            }
        }
        a.recycle();
        mPaint=new Paint();
        mRect=new Rect();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(mCircleWidth);//设置圆环的宽度
        mPaint.setStrokeCap(Paint.Cap.ROUND);//定义线段断电形状为圆头
        mPaint.setStyle(Paint.Style.STROKE);//设置为空心
        int centre=getWidth()/2;
        int radius=centre-mCircleWidth/2;
        drawOval(canvas,centre,radius);
        /**
         * 计算内切正方形的位置
         */
        int relRadius = radius - mCircleWidth / 2;// 获得内圆的半径
        /**
         * 内切正方形的距离顶部 = mCircleWidth + relRadius - √2 / 2
         */
        mRect.left = (int) (relRadius - Math.sqrt(2) * 1.0f / 2 * relRadius) + mCircleWidth;
        /**
         * 内切正方形的距离左边 = mCircleWidth + relRadius - √2 / 2
         */
        mRect.top = (int) (relRadius - Math.sqrt(2) * 1.0f / 2 * relRadius) + mCircleWidth;
        mRect.bottom = (int) (mRect.left + Math.sqrt(2) * relRadius);
        mRect.right = (int) (mRect.left + Math.sqrt(2) * relRadius);

        /**
         * 如果图片比较小，那么根据图片的尺寸放置到正中心
         */
        if (mImage.getWidth() < Math.sqrt(2) * relRadius)
        {
            mRect.left = (int) (mRect.left + Math.sqrt(2) * relRadius * 1.0f / 2 - mImage.getWidth() * 1.0f / 2);
            mRect.top = (int) (mRect.top + Math.sqrt(2) * relRadius * 1.0f / 2 - mImage.getHeight() * 1.0f / 2);
            mRect.right = (int) (mRect.left + mImage.getWidth());
            mRect.bottom = (int) (mRect.top + mImage.getHeight());

        }
        // 绘图
        canvas.drawBitmap(mImage, null, mRect, mPaint);


    }

    private void drawOval(Canvas canvas, int centre, int radius) {
        float itemSize=(360*1.0f-mCount*mSplitSize)/mCount;
        RectF oval=new RectF(centre-radius,centre-radius,centre+radius,centre+radius);//用于定于圆弧的形状和大小的界限
        mPaint.setColor(mFirstColor);
        for(int i=0;i<mCount;i++){
            canvas.drawArc(oval,i*(itemSize+mSplitSize),itemSize,false,mPaint);
        }
        mPaint.setColor(mSecondColor);
        for(int i=0;i<mCurrentCount;i++){
            canvas.drawArc(oval,i*(itemSize+mSplitSize),itemSize,false,mPaint);
        }

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                    xDown=(int)event.getY();
                break;
            case MotionEvent.ACTION_UP:
                    xUp=(int)event.getY();
                    if(xUp>xDown){
                        down();
                    }else {
                        up();
                    }

                break;
        }
        return true;
    }
    public void up(){
        mCurrentCount++;
        postInvalidate();
    }
    public void down(){
        mCurrentCount--;
        postInvalidate();
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
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        super.setOnClickListener(l);
    }
}
