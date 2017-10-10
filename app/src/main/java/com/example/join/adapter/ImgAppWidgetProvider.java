package com.example.join.adapter;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.john.customviewtitle.R;

/**
 * 用途：
 * 作者：Created by liulei on 2017/10/10.
 * 邮箱：liulei2@aixuedai.com
 */


public class ImgAppWidgetProvider extends AppWidgetProvider {
    public static final String CLICK_ACTION = "cn.milk.androiddevartnote.action.click";
    private static int index;

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (CLICK_ACTION.equals(intent.getAction())) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_provider);
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            updateView(context, remoteViews, appWidgetManager);

        }
    }

    public void updateView(Context context, RemoteViews remoteViews, AppWidgetManager appWidgetManager) {
        index= (int) (Math.random()*3);
        if (index==1){
            remoteViews.setImageViewResource(R.id.iv_demo,R.drawable.a);
        }else if(index==2){
            remoteViews.setImageViewResource(R.id.iv_demo,R.drawable.b);

        }else {
            remoteViews.setImageViewResource(R.id.iv_demo,R.drawable.c);

        }
        Intent clickIntent = new Intent();
        clickIntent.setAction(CLICK_ACTION);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, clickIntent, 0);
        remoteViews.setOnClickPendingIntent(R.id.iv, pendingIntent);
        appWidgetManager.updateAppWidget(new ComponentName(context, ImgAppWidgetProvider.class), remoteViews);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_provider);
        updateView(context, remoteViews, appWidgetManager);
    }
}
