package com.example.join.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.john.customviewtitle.R;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by Liu on 2016/3/16.
 * TODO:
 */
public class CustomTitleView extends View {

    private String mTitleName;
    private int mTittleSize;
    private int mTitleColor;
    //绘制时控制文本的范围
    private Rect mRect;
    //画笔
    private Paint mPaint;
    private CallBack mBack;

    public CallBack getmBack() {
        return mBack;
    }

    public void setmBack(CallBack mBack) {
        this.mBack = mBack;
    }

    public CustomTitleView(Context context) {
        super(context,null);
    }

    public CustomTitleView(Context context, AttributeSet attrs) {
        super(context, attrs,0);
        init(context, attrs, 0);

    }

    public CustomTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
     init(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        EXACTLY：一般是设置了明确的值或者是MATCH_PARENT
//        AT_MOST：表示子布局限制在一个最大值内，一般为WARP_CONTENT
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        int heigtSize=MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        if(widthMode==MeasureSpec.EXACTLY){
            width=widthSize;
        }
        else{
            mPaint.setTextSize(mTittleSize);
            mPaint.getTextBounds(mTitleName,0,mTitleName.length(),mRect);
            float texWidth=mRect.width();
            int desired=(int)(getPaddingLeft()+texWidth+getPaddingRight());
            width=desired;
        }
        if(heightMode==MeasureSpec.EXACTLY){
            height=heigtSize;
        }
        else{
            mPaint.setTextSize(mTittleSize);
            mPaint.getTextBounds(mTitleName,0,mTitleName.length(),mRect);
            float texHeight=mRect.height();
            int desired=(int)(getPaddingLeft()+texHeight+getPaddingRight());
            height=desired;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(getResources().getColor(R.color.colorPrimary));
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(),mPaint);
        mPaint.setColor(mTitleColor);
        canvas.drawText(mTitleName, getWidth() / 2 - mRect.width() / 2, getHeight() / 2 + mRect.height() / 2, mPaint);
    }
    public void  init(Context context, AttributeSet attrs,int defStyleAttr){
       TypedArray a=context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomTitleView, defStyleAttr,0);
       int n=a.getIndexCount();
       for(int i=0;i<n;i++){
           int arrt=a.getIndex(i);
           switch (arrt){
               case R.styleable.CustomTitleView_titleName:
                   mTitleName=a.getString(i);
                   break;
               case R.styleable.CustomTitleView_titleSize:
                   mTittleSize = a.getDimensionPixelSize(arrt, (int) TypedValue.applyDimension(
                           TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                   break;
               case R.styleable.CustomTitleView_titlleColor:
                   mTitleColor=a.getColor(arrt, Color.BLACK);
                   break;
           }
       }
       a.recycle();
       mPaint=new Paint();
       mPaint.setTextSize(mTittleSize);
       mRect=new Rect();
       //获得文本的长宽
       mPaint.getTextBounds(mTitleName, 0, mTitleName.length(), mRect);
       this.setOnClickListener(new OnClickListener() {
           @Override
           public void onClick(View v) {
                mTitleName=getRandomText();
                mBack.onCBack(mTitleName);
                postInvalidate();
           }
       });
   }
    public String getRandomText()
    {
        Random random = new Random();
        Set<Integer> set = new HashSet<Integer>();
        while (set.size() < 4)
        {
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }
        StringBuffer sb = new StringBuffer();
        for (Integer i : set)
        {
            sb.append("" + i);
        }

        return sb.toString();
    }
    public interface  CallBack{
        void onCBack(String number);
    }
}
