package com.example.join.util;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.john.customviewtitle.R;

/**
 * Created by Liu on 2016/3/31.
 * TODO:
 */
public class CustomPopView  extends LinearLayout{
    private int type;
    private ImageButton mIvBtn;
    private View mask;
    private int mMargin;
    private float mDensity;
    private int[] ids={
        R.id.contract_all,
        R.id.contract_this_week,
        R.id.contract_next_week};
    private int[]colr={
        R.drawable.btn_bule,
        R.drawable.btn_green,
        R.drawable.btn_red};
    private  int[] name={
            R.string.contract_all,
            R.string.contract_this_week,
            R.string.contract_next_week};

    private Button[] mMenu=new Button[3];
    private Boolean open=false;

    private MenuBackLister mMenuBack;

    public void setmMenuBack(MenuBackLister mMenuBack) {
        this.mMenuBack = mMenuBack;
    }

    public interface MenuBackLister{
        void callBack(String id);
    }
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public View getMask() {
        return mask;
    }

    public void setMask(View mask) {
        this.mask = mask;
    }

    public CustomPopView(Context context,int type) {
        super(context);
        this.type=type;
        init();
    }

    public CustomPopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomPopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    public void init(){
        setOrientation(VERTICAL);
        DisplayMetrics metrics=new DisplayMetrics();
        WindowManager wm=(WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        mDensity=metrics.density;
        mMargin=(int)(mDensity*10);
        initButton();
        initMenu();
    }

    public void initButton() {
        mIvBtn=new ImageButton(getContext());
        LayoutParams lp=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.topMargin=mMargin;
         lp.gravity= Gravity.BOTTOM;
        int left= getContext().getResources().getDimensionPixelOffset(R.dimen.pop_left);

        mIvBtn.setLayoutParams(lp);
        mIvBtn.setBackgroundColor(Color.TRANSPARENT);
        mIvBtn.setPadding(left, left, left, left);
        mIvBtn.setImageResource(R.drawable.ic_status_close);

        mIvBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (open) {
                    close();
                } else {
                    open();
                }
            }
        });
        addView(mIvBtn);
    }
    public void initMenu() {
        int left= getContext().getResources().getDimensionPixelOffset(R.dimen.pop_left);
        for(int i=0;i<mMenu.length;i++){
            final Button mbtn=new Button(getContext());
            LayoutParams lp=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.topMargin=mMargin;
            lp.gravity= Gravity.CENTER_HORIZONTAL;
            mbtn.setGravity(Gravity.CENTER);
            mbtn.setLayoutParams(lp);
            mbtn.setTextColor(Color.WHITE);
            mbtn.setPadding(left, left, left, left);
            mbtn.setBackgroundResource(colr[i]);
            mbtn.setId(ids[i]);
            if(type==0) {
                mbtn.setText(name[i]);
            }
            mbtn.setTextSize(11);
            mbtn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    mMenuBack.callBack(mbtn.getText().toString());
                    close();
                }
            });
            mMenu[i]=mbtn;
        }
    }

    public void open(){
        open=true;
        if(mask!=null){
            mask.setVisibility(View.VISIBLE);
        }
        mIvBtn.setImageResource(R.drawable.ic_status_open);
        for(int i=mMenu.length-1;i>=0;i--){
                addView(mMenu[i],0);
        }
    }
    public void close(){
        open=false;
        if(mask==null){
            mask.setVisibility(View.GONE);
        }
        mIvBtn.setImageResource(R.drawable.ic_status_close);
        for(int i=mMenu.length-1;i>=0;i--){
                removeView(mMenu[i]);
        }
    }
}
