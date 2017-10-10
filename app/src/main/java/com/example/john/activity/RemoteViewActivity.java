package com.example.john.activity;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RemoteViews;

import com.example.john.customviewtitle.R;

/**
 * 用途：
 * 作者：Created by liulei on 2017/10/10.
 * 邮箱：liulei2@aixuedai.com
 */


public class RemoteViewActivity extends AppCompatActivity {
    NotificationManager manager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_view);
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        final RemoteViews remoteViews = new RemoteViews("com.example.john.customviewtitle", R.layout.remote_view_title);
        Intent intent1 = new Intent(RemoteViewActivity.this, MainActivity.class);
        final PendingIntent mPendingIntent1 = PendingIntent.getActivity(RemoteViewActivity.this, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        Intent intent2 = new Intent(RemoteViewActivity.this, RemoteViewActivity.class);
        final PendingIntent mPendingIntent2 = PendingIntent.getActivity(RemoteViewActivity.this, 0, intent2, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.btn_home,mPendingIntent1);
        remoteViews.setOnClickPendingIntent(R.id.btn_now,mPendingIntent2);

        findViewById(R.id.btn_toast).setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                Notification mNotife = new Notification.Builder(getBaseContext())
                        .setContentTitle("标题")
                        .setContentText("标题内容区域")
                        .setContent(remoteViews)
                        .setContentIntent(mPendingIntent1)
                        .setSmallIcon(R.drawable.ic_status_close)
                        .build();
                manager.notify(1, mNotife);

            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }
}
