package com.example.join.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liu on 2016/3/25.
 * TODO:
 */
public class CustomViewGroupsView extends android.view.ViewGroup {
    private static final String TAG = "FlowLayout";
    private List<List<View>> mAllViews = new ArrayList<List<View>>();
    private List<Integer> mLineHeight = new ArrayList<Integer>();

    public CustomViewGroupsView(Context context) {
        super(context, null);
    }

    public CustomViewGroupsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomViewGroupsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    //重写父类的该方法，这样就可以为viewgroup指定了其LayoutParams为MarginLayoutParams
    //参数  1.  AttributeSet attrs       xml解析inflate时生成和容器类型匹配的布局LayoutParams
    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    //  计算当前的viewgroup的宽高
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mAllViews.clear();
        mLineHeight.clear();
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        //计算所有child的宽和高
        measureChildren(widthSize, heightSize);
        int width = 0;
        int height = 0;
        int lineWidth = 0;
        int lineHeight = 0;
        int count = getChildCount();
        /**
         * 根据childView计算出宽和高，以及设置的margin计算容器的宽和高，主要用于容器是warp_content时
         */
        List<View> lineViews = new ArrayList<View>();
        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);//计算每个child视图的长与宽
            //当前view的宽和高
            MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();
            int childWidth = childView.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = childView.getMeasuredHeight() + lp.bottomMargin + lp.topMargin;
            //如果当前的child超出最大宽度，则得到的目前最大宽度给width，累加heigth,然后开始新的child
            if (lineWidth + childWidth > widthSize) {
                width = Math.max(lineWidth, childWidth);//取最大的
                height += childHeight;//叠加高度
                lineWidth = childWidth;//重新开始记录
                lineHeight = childHeight;
                mAllViews.add(lineViews);
                mLineHeight.add(lineHeight);
                lineViews = new ArrayList<View>();
                lineViews.add(childView);
            } else {
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight, childHeight);
                lineViews.add(childView);

            }
            if (i == count - 1) {
                width = Math.max(width, lineWidth);
                height += lineHeight;
            }
        }
        mLineHeight.add(lineHeight);
        mAllViews.add(lineViews);
        setMeasuredDimension((widthMode == MeasureSpec.EXACTLY) ? widthSize : width, (heightMode == MeasureSpec.EXACTLY) ? heightSize : height);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    //对齐所有childView根据其宽和高，以及margin进行布局
    @Override
    protected void onLayout(boolean changed, int lefts, int tops, int rights, int bottom) {
        int lineHeight = 0;
        List<View> lineViews = new ArrayList<View>();
        int left = 0;
        int top = 0;
        // 得到总行数
        int lineNums = mAllViews.size();
        for (int i = 0; i < lineNums; i++) {
            // 每一行的所有的views
            lineViews = mAllViews.get(i);
            // 当前行的最大高度
            lineHeight = mLineHeight.get(i);

            // 遍历当前行所有的View
            for (int j = 0; j < lineViews.size(); j++) {
                View child = lineViews.get(j);
                if (child.getVisibility() == View.GONE) {
                    continue;
                }
                MarginLayoutParams lp = (MarginLayoutParams) child
                        .getLayoutParams();

                //计算childView的left,top,right,bottom
                int lc = left + lp.leftMargin;
                int tc = top + lp.topMargin;
                int rc = lc + child.getMeasuredWidth();
                int bc = tc + child.getMeasuredHeight();
                child.layout(lc, tc, rc, bc);
                left += child.getMeasuredWidth() + lp.rightMargin
                        + lp.leftMargin;
            }
            left = 0;
            top += lineHeight;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }
}
