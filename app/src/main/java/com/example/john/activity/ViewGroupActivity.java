package com.example.john.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.Keyframe;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.john.customviewtitle.R;
import com.example.join.util.CustomViewGroupsView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liu on 2016/3/25.
 * TODO:
 */
public class ViewGroupActivity extends Activity {
    private List<String> mList = new ArrayList<String>();
    private CustomViewGroupsView container;
    private Button mBtnAdd, mBtnRemove;
    private ViewGroup.MarginLayoutParams params = new LinearLayout.LayoutParams(ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT);
    private LayoutTransition mTransitioner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setData();

        setContentView(R.layout.activity_viewgroup);
        mBtnAdd = (Button) findViewById(R.id.btn_all);
        mBtnRemove = (Button) findViewById(R.id.btn_remove);
        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addText(mList.get((int) (Math.random() * mList.size())));
            }
        });
        mBtnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                container.removeViewAt(container.getChildCount() - 1);
            }
        });
        container = (CustomViewGroupsView) findViewById(R.id.vg);

        initTransition();
        initLayoutTransition();
        for (int i = 0; i < mList.size(); i++) {
            addText(mList.get(i));
        }
    }

    private void initTransition() {
        mTransitioner = new LayoutTransition();
        container.setLayoutTransition(mTransitioner);
    }

    public void initLayoutTransition() {
        /**
         * view出现时 view自身的动画效果
         */
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(null, "rotationY", 90F, 0F).
                setDuration(mTransitioner.getDuration(LayoutTransition.APPEARING));
        mTransitioner.setAnimator(LayoutTransition.APPEARING, animator1);

        /**
         * view 消失时，view自身的动画效果
         */
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(null, "rotationX", 0F, 90F, 0F).
                setDuration(mTransitioner.getDuration(LayoutTransition.DISAPPEARING));
        mTransitioner.setAnimator(LayoutTransition.DISAPPEARING, animator2);
        /**
         * view 动画改变时，布局中的每个子view动画的时间间隔
         */
        mTransitioner.setStagger(LayoutTransition.CHANGE_APPEARING, 30);
        mTransitioner.setStagger(LayoutTransition.CHANGE_DISAPPEARING, 30);
        /**
         * 组合属性动画
         */
        PropertyValuesHolder pvhLeft =
                PropertyValuesHolder.ofInt("left", 0, 1);
        PropertyValuesHolder pvhTop =
                PropertyValuesHolder.ofInt("top", 0, 1);
        PropertyValuesHolder pvhRight =
                PropertyValuesHolder.ofInt("right", 0, 1);
        PropertyValuesHolder pvhBottom =
                PropertyValuesHolder.ofInt("bottom", 0, 1);


        /**
         * view出现时，导致整个布局改变的动画
         */
        PropertyValuesHolder animator3 = PropertyValuesHolder.ofFloat("scaleX", 1F, 2F, 1F);
        final ObjectAnimator changeIn = ObjectAnimator.ofPropertyValuesHolder(
                this, pvhLeft, pvhTop, pvhRight, pvhBottom, animator3).
                setDuration(mTransitioner.getDuration(LayoutTransition.CHANGE_APPEARING));
        changeIn.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                View view = (View) ((ObjectAnimator) animation).getTarget();
                view.setScaleX(1.0f);
            }
        });
        mTransitioner.setAnimator(LayoutTransition.CHANGE_APPEARING, changeIn);


        /**
         * view消失，导致整个布局改变时的动画
         */
        Keyframe kf0 = Keyframe.ofFloat(0f, 0f);
        Keyframe kf1 = Keyframe.ofFloat(.5f, 2f);
        Keyframe kf2 = Keyframe.ofFloat(1f, 0f);
        PropertyValuesHolder pvhRotation =
                PropertyValuesHolder.ofKeyframe("scaleX", kf0, kf1, kf2);
        final ObjectAnimator changeOut = ObjectAnimator.ofPropertyValuesHolder(
                this, pvhLeft, pvhTop, pvhRight, pvhBottom, pvhRotation).
                setDuration(mTransitioner.getDuration(LayoutTransition.CHANGE_DISAPPEARING));
        changeOut.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                View view = (View) ((ObjectAnimator) animation).getTarget();
                view.setScaleX(1.0f);
            }
        });
        mTransitioner.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, changeOut);
    }

    public void addText(String str) {
        TextView child = new TextView(this);
        params.setMargins(15, 15, 15, 15);
        child.setLayoutParams(params);
        child.setBackgroundResource(R.drawable.flag_01);
        child.setTextColor(Color.GREEN);
        child.setText(str.toString());
        initEvent(child);
        container.addView(child);
        container.requestLayout();
    }

    public void initEvent(final TextView child) {
        child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ViewGroupActivity.this, child.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setData() {
        mList.add("密码学");
        mList.add("数据结构");
        mList.add("数据库");
        mList.add("高等数学");
        mList.add("编译原理");
        mList.add("计算机组成系统");
        mList.add("网络技术");
        mList.add("C语言");
        mList.add("Java基础");

    }
}
