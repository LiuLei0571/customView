package com.example.john.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.john.customviewtitle.R;
import com.example.join.util.CustomPopView;

/**
 * Created by Liu on 2016/3/31.
 * TODO:
 */
public class PopCustomActivity extends Activity{
    private FrameLayout mF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popcustom);
        mF=(FrameLayout)this.findViewById(R.id.fmlyt);
        initView();
    }
    public void initView(){
        FrameLayout mask=new FrameLayout(PopCustomActivity.this);
     //   mask.setVisibility(View.GONE);
      //  mask.setBackgroundColor(Color.GREEN);
        mF.addView(mask, FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        CustomPopView popup=new CustomPopView(getApplicationContext(),0);
        DisplayMetrics metrics=new DisplayMetrics();
        WindowManager wm=(WindowManager)getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        int width=(int)(metrics.density*100);
        int marginRight=(int)(metrics.density*10);
        int marginBottom=(int)(metrics.density*43);
        FrameLayout.LayoutParams lp=new FrameLayout.LayoutParams(width, FrameLayout.LayoutParams.MATCH_PARENT);
        lp.rightMargin=marginRight;
        lp.bottomMargin=marginBottom;
        lp.gravity= Gravity.RIGHT|Gravity.BOTTOM;
        popup.setLayoutParams(lp);
        popup.setMask(mask);
        popup.setGravity(Gravity.BOTTOM);
        popup.setmMenuBack(new CustomPopView.MenuBackLister() {
            @Override
            public void callBack(String id) {
                Toast.makeText(PopCustomActivity.this, id, Toast.LENGTH_LONG).show();
            }
        });
        mF.addView(popup);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
    }
}
