package com.example.join.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.EditText;

import com.example.john.customviewtitle.R;

/**
 * 用途：
 * 作者：Created by john on 2016/8/15.
 * 邮箱：liulei2@aixuedai.com
 */

public class PayPasswordView extends EditText {
    private Paint paint = new Paint();

    public PayPasswordView(Context context) {
        super(context);
    }

    public PayPasswordView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PayPasswordView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(getContext().getResources().getColor(R.color.white));
        paint.setAntiAlias(true);
        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.STROKE);
        float x = getWidth();
        float y = getHeight();
        canvas.drawRect(0, 0, x, y, paint);
        float length = x / 6;
        float smallX = 0;
        paint.setColor(getContext().getResources().getColor(R.color.colorAccent));
        for (int i = 0; i < 6; i++) {
            canvas.drawRect(smallX, 0, smallX + length, y, paint);
            smallX += length;
        }
        paint.setColor(getContext().getResources().getColor(R.color.colorBack));
        paint.setStyle(Paint.Style.FILL);
        float cicle = length / 2;
        float radius = cicle / 4;
        int teLength = getText().toString().length();
        for (int i = 0; i < teLength; i++) {
            canvas.drawCircle(cicle, y / 2, radius, paint);
            cicle += length;
        }
    }
}
