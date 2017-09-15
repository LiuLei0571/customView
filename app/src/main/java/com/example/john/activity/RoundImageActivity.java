package com.example.john.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.example.john.customviewtitle.R;

/**
 * 用途：
 * 作者：Created by john on 2017/7/6.
 * 邮箱：liulei2@aixuedai.com
 */


public class RoundImageActivity extends Activity {
    private ImageView mIv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_image);
        mIv = (ImageView) findViewById(R.id.iv);
//        getRoundCornerBitmap();
    }

    private void getRoundCornerBitmap() {
        Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.back);
        int widthSize = mBitmap.getWidth();
        int heightSize = mBitmap.getHeight();
        Bitmap mBitmapBack = Bitmap.createBitmap(widthSize, heightSize, Bitmap.Config.ARGB_8888);
        Canvas mCanvas = new Canvas(mBitmapBack);
        Paint mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        Rect rect = new Rect(0, 0, widthSize, heightSize);
        RectF rectF = new RectF(rect);
        mCanvas.drawRoundRect(rectF, 50, 50, mPaint);
        PorterDuffXfermode mPorter = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        mPaint.setXfermode(mPorter);
        mCanvas.drawBitmap(mBitmap, rect, rect, mPaint);
        mIv.setImageBitmap(mBitmapBack);
    }
}
