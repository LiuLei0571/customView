package com.example.join.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.john.customviewtitle.R;

/**
 * Created by Liu on 2016/3/17.
 * TODO:
 */
public class CustomImageView extends View {
    private String mTitleName;
    private int mTittleSize;
    private int mTitleColor;
    private Bitmap mImage;
    private int mImageScale;
    private Paint mPaint;
    private Rect mRectText, rect;
    int mWidth;
    int mHeight;
    private static final int IMAGE_SCALE_FITXY = 0;
    private static final int IMAGE_SCALE_CENTER = 1;

    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        init(context, attrs, 0);
    }

    public CustomImageView(Context context) {
        super(context, null);

    }

    public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);

    }

    public void init(Context context, AttributeSet attrs, int defStyleAttr) {

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomImageView, defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.CustomImageView_image:
                    mImage = BitmapFactory.decodeResource(getResources(), a.getResourceId(attr, 0));
                    break;
                case R.styleable.CustomImageView_imageScaleType:
                    mImageScale = a.getInt(attr, 0);
                    break;
                case R.styleable.CustomImageView_titleText:
                    mTitleName = a.getString(attr);
                    break;
                case R.styleable.CustomImageView_titleTextColors:
                    mTitleColor = a.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.CustomImageView_titleTextSize:
                    mTittleSize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                            16, getResources().getDisplayMetrics()));
                    break;

            }
        }
        a.recycle();
        mPaint = new Paint();
        mPaint.setTextSize(mTittleSize);
        mRectText = new Rect();
        rect = new Rect();
        //获得文本的长宽;
        mPaint.getTextBounds(mTitleName, 0, mTitleName.length(), mRectText);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heigtSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        } else {
            //取最大的
            int widthImage = getPaddingLeft() + mImage.getWidth() + getPaddingRight();
            int widthText = getPaddingLeft() + mRectText.width() + getPaddingRight();
            if (widthMode == MeasureSpec.AT_MOST) {

                int desirWidth = Math.max(widthImage, widthText);
                mWidth = Math.min(desirWidth, widthSize);
            }

        }
        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heigtSize;
        } else {
            int heightImage = getPaddingTop() + mImage.getHeight() + getPaddingBottom() + mRectText.height();
            if (heightMode == MeasureSpec.AT_MOST) {
                mHeight = Math.min(heightImage, heigtSize);
            }
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setStrokeWidth(4);//画笔的粗细
        mPaint.setStyle(Paint.Style.STROKE);//设置画笔的粗体
        mPaint.setColor(Color.GREEN);//设置画笔的颜色
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);//画一个矩形
        rect.left = getPaddingLeft();
        rect.right = mWidth - getPaddingLeft();
        rect.top = getPaddingTop();
        rect.bottom = mHeight - getPaddingBottom();
        if (mRectText.width() > mWidth) {
            TextPaint paint = new TextPaint(mPaint);
            String msg = TextUtils.ellipsize(mTitleName, paint, (float) mWidth - getPaddingLeft() - getPaddingRight(),
                    TextUtils.TruncateAt.END).toString();
            canvas.drawText(msg, getPaddingLeft(), mHeight - getPaddingBottom(), mPaint);

        } else {
            //正常情况，将字体居中
            canvas.drawText(mTitleName, mWidth / 2 - mRectText.width() * 1.0f / 2, mHeight - getPaddingBottom(), mPaint);
        }

        //取消使用掉的快
        rect.bottom -= mRectText.height();

        if (mImageScale == IMAGE_SCALE_FITXY) {
            canvas.drawBitmap(mImage, null, rect, mPaint);
        } else {
            //计算居中的矩形范围
            rect.left = mWidth / 2 - mImage.getWidth() / 2;
            rect.right = mWidth / 2 + mImage.getWidth() / 2;
            rect.top = (mHeight - mRectText.height()) / 2 - mImage.getHeight() / 2;
            rect.bottom = (mHeight - mRectText.height()) / 2 + mImage.getHeight() / 2;

            canvas.drawBitmap(mImage, null, rect, mPaint);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
}
