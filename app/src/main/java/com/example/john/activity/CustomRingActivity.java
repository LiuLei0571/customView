package com.example.john.activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.john.customviewtitle.R;

/**
 * Created by Liu on 2016/3/22.
 * TODO:
 */
public class CustomRingActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring);
        getBaseContext();
        getApplication();
        getApplicationContext();
    }
}
