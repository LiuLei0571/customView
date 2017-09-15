package com.example.join.util;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.john.customviewtitle.R;

/**
 * 用途：
 * 作者：Created by john on 2017/7/27.
 * 邮箱：liulei2@aixuedai.com
 */


public class TextWithImageView extends LinearLayout {
    private int number;
    private String title;
    private int resId;
    private TextView mTvNumber;
    private TextView mTVTitle;
    private ImageView mIvPhoto;
    private ImageView mIvArrow;
    private Context mContext;
    private Animation mArrowAnimatorDown;
    private Animation mArrowAnimatorUp;
    private LinearLayout mLytView;
    private int parentWidthSize;
    private int parentHeightSize;

    public void setNumber(int number) {
        this.number = number;
        mTvNumber.setText(number + "");
        refresh();
    }

    public void setTitle(String title) {
        this.title = title;
        mTVTitle.setText(title);
        refresh();
    }

    public void setResId(int resId) {
        this.resId = resId;
        mIvPhoto.setImageDrawable(mContext.getResources().getDrawable(resId));
        refresh();

    }

    public TextWithImageView(Context context) {
        this(context, null);
    }

    public TextWithImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextWithImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.view_text_image, this);
        initView();
        initData();
    }

    private void initData() {
        mArrowAnimatorDown = AnimationUtils.loadAnimation(mContext, R.anim.roate_down);
        mArrowAnimatorUp = AnimationUtils.loadAnimation(mContext, R.anim.roate_up);

    }

    public void setContent(int resId) {
        View view = LayoutInflater.from(mContext).inflate(resId, null);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        mLytView.addView(view);
    }

    private void initView() {
        mTvNumber = (TextView) findViewById(R.id.tv_number);
        mTVTitle = (TextView) findViewById(R.id.tv_title);
        mIvPhoto = (ImageView) findViewById(R.id.iv_photo);
        mIvArrow = (ImageView) findViewById(R.id.iv_arrow);
        mLytView = (LinearLayout) findViewById(R.id.lyt_view);
        mTVTitle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                rotateView();
            }
        });
    }

    private void rotateView() {
        if (mIvArrow.getTag() == null || mIvArrow.getTag().equals(true)) {
            mIvArrow.setTag(false);
            mIvArrow.startAnimation(mArrowAnimatorDown);
            expand(mLytView);
        } else {
            mIvArrow.setTag(true);
            mIvArrow.startAnimation(mArrowAnimatorUp);
            collapse(mLytView);
        }
    }

    private void expand(final View mLytView) {
        mLytView.measure(parentWidthSize, parentHeightSize);
        int measureWidth = mLytView.getMeasuredWidth();
        final int measureHeight = mLytView.getMeasuredHeight();
        mLytView.getLayoutParams().width = measureWidth;
        mLytView.getLayoutParams().height=0;
        mLytView.setVisibility(VISIBLE);
        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    mLytView.getLayoutParams().height = measureHeight;
                } else {
                    mLytView.getLayoutParams().height = (int) (interpolatedTime * measureHeight);
                }
                mLytView.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation.setDuration(3000);
        mLytView.startAnimation(animation);
    }

    private void collapse(final View mLytView) {
        final int measureHeight = mLytView.getMeasuredHeight();
        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    mLytView.getLayoutParams().height = measureHeight;
                    mLytView.setVisibility(GONE);
                } else {
                    mLytView.getLayoutParams().height = measureHeight - (int) (interpolatedTime * measureHeight);
                }
                mLytView.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation.setDuration(3000);
        mLytView.startAnimation(animation);
    }


    @Override
    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        parentWidthSize = widthMeasureSpec;
        parentHeightSize = heightMeasureSpec;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

    }

    public void refresh() {
        invalidate();
    }
}
