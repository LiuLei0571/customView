package com.example.join.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.john.customviewtitle.R;

/**
 * 用途：
 * 作者：Created by john on 2016/8/4.
 * 邮箱：liulei2@aixuedai.com
 */

public class ContactView extends View {
    public final String[] b = {"", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
    int choose = -1;
    Paint paint = new Paint();
    boolean showBkg = false;

    public ContactView(Context context) {
        super(context);
    }

    public ContactView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ContactView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (showBkg) {
            canvas.drawColor(Color.parseColor("#32000000"));
        }

        int height = getHeight();
        int width = getWidth();
        int singleHeight = (height * 2) / (b.length * 2 - 1);
        for (int i = 0; i < b.length; i++) {
            paint.setColor(Color.WHITE);
            paint.setTypeface(Typeface.DEFAULT_BOLD);
            paint.setTextSize(getContext().getResources().getDimension(R.dimen.text_normal));
            paint.setAntiAlias(true);
            if (i == choose) {
                paint.setColor(getContext().getResources().getColor(R.color.colorAccent));
                paint.setFakeBoldText(true);
            }
            float xPos = width / 2 - paint.measureText(b[i]) / 2;
            float yPos = singleHeight * i;
            canvas.drawText(b[i], xPos, yPos, paint);
            paint.reset();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float eventY = event.getY();
        int locationY = (int) ((eventY / getHeight()) * b.length);
        String str = b[locationY];
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (locationY > 0 && locationY < b.length) {
                    choose = locationY;
                    onChangeListener.getStr(str);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (locationY > 0 && locationY < b.length) {
                    choose = locationY;
                    onChangeListener.getStr(str);
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                choose = -1;
                showBkg = false;
                break;
        }
        invalidate();
        return true;
    }

    public OnChangeListener onChangeListener;

    public void setOnChangeListener(OnChangeListener onChangeListener) {
        this.onChangeListener = onChangeListener;
    }

    public interface OnChangeListener {
        void getStr(String str);
    }

}
