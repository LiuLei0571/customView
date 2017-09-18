package com.example.john.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.example.john.customviewtitle.R;

/**
 * Created by Liu on 2016/3/17.
 * TODO:
 */
public class MainActivity extends Activity {
    private Button mBtnImage, mBtnVerification, mBtnRing, mBtnSound, mBtnTag, mBtnPop, mBtnSwipe, mBtnContacts, mBtnViewGroup;
    private Handler hanlder = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnVerification = (Button) findViewById(R.id.btn_verification);
        mBtnRing = (Button) findViewById(R.id.btn_ring);
        mBtnImage = (Button) findViewById(R.id.btn_image);
        mBtnSound = (Button) findViewById(R.id.btn_sound);
        mBtnTag = (Button) findViewById(R.id.btn_view_tag);
        mBtnPop = (Button) findViewById(R.id.btn_view_pop);
        mBtnSwipe = (Button) findViewById(R.id.btn_view_swipeList);
        mBtnViewGroup = (Button) findViewById(R.id.viewgroup);
        mBtnVerification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hanlder.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent it = new Intent(MainActivity.this, VerificationActivity.class);
                        startActivity(it);
                    }
                }, 1000);


            }
        });
        mBtnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hanlder.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent it = new Intent(MainActivity.this, ImageActivity.class);
                        startActivity(it);
                    }
                }, 1000);
            }
        });
        mBtnRing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hanlder.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent it = new Intent(MainActivity.this, CustomRingActivity.class);
                        startActivity(it);
                    }
                }, 1000);
            }
        });
        mBtnSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CustomSoundActivity.class);
                startActivity(intent);
            }
        });
        mBtnTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewGroupActivity.class);
                startActivity(intent);
            }
        });
        mBtnPop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PopCustomActivity.class);
                startActivity(intent);
            }
        });
        mBtnSwipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewPagerActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.taiji).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CustomTaijiActivity.class));
            }
        });
        findViewById(R.id.contacts).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ContactActivity.class));
            }
        });
        findViewById(R.id.paypassword).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PayPasswordChangeActivity.class));
            }
        });
        findViewById(R.id.coordinatorLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StickActivity.class));
            }
        });
        findViewById(R.id.progressLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CustomProgressActivity.class));
            }
        });
        mBtnViewGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ViewGroupDemoActivity.class));

            }
        });
        findViewById(R.id.view_round).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RoundImageActivity.class));
            }
        });
        findViewById(R.id.btn_text_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TextWithImageActivity.class));

            }
        });
        findViewById(R.id.btn_touch_demo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TouchSubActivity.class));

            }
        });
        findViewById(R.id.btn_bubble).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BubbleActivity.class));

            }
        });
    }
}
