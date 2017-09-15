package com.example.john.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.john.customviewtitle.R;
import com.example.join.adapter.ContactAdapter;
import com.example.join.helper.ContactAsyncQueryHandler;
import com.example.join.util.ContactView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 用途：
 * 作者：Created by john on 2016/8/4.
 * 邮箱：liulei2@aixuedai.com
 */

public class SelectContactActivity extends Activity implements AdapterView.OnItemClickListener {
    public static OnContactCallBack callack;
    public ListView mLvContact;
    public ContactView mContactView;
    public ContactAdapter mAdapter;
    public HashMap<String, Integer> hashMap = new HashMap<>();
    public String[] alepth;
    public TextView overlay;
    private WindowManager windowManager;
    private Handler handler = new Handler();
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivty_select_contact);
        initView();
        initData();
        showWindows();
    }

    public void initView() {
        runnable = new Runnable() {
            @Override
            public void run() {
                if (overlay != null) {
                    overlay.setVisibility(View.GONE);
                }
            }
        };
        mLvContact = (ListView) findViewById(R.id.lv_contact);
        mLvContact.setOnItemClickListener(this);
        mContactView = (ContactView) findViewById(R.id.custom_contact);
        mContactView.setOnChangeListener(new ContactView.OnChangeListener() {
            @Override
            public void getStr(String str) {
                if (getMapValue(hashMap, str)) {
                    int postion = hashMap.get(str);
                    mLvContact.setSelection(postion);
                    overlay.setText(alepth[postion]);
                    overlay.setVisibility(View.VISIBLE);
                    handler.removeCallbacks(runnable);
                    handler.postDelayed(runnable, 2000);
                }

            }
        });
    }

    public void initData() {
        mAdapter = new ContactAdapter(new ArrayList<ContentValues>(), SelectContactActivity.this);
        ContactAsyncQueryHandler handler = new ContactAsyncQueryHandler(getContentResolver());
        handler.setListener(new ContactAsyncQueryHandler.OnContactLoadingCompleteListener() {
            @Override
            public void onComplete(List<ContentValues> contentValues) {
                mAdapter.setContentValues(contentValues);
                mLvContact.setAdapter(mAdapter);
                initContact(contentValues);
            }
        });
        handler.start();
    }

    public void initContact(List<ContentValues> contentValues) {
        int postion = 0;
        alepth = new String[contentValues.size()];
        List<String> firstLetter = new ArrayList<>();
        for (ContentValues bean : contentValues) {
            firstLetter.add(getFirstLetter(bean.getAsString("sort_key")));
        }
        for (int i = 1; i < firstLetter.size(); i++) {
            if (!(firstLetter.get(i).equals(firstLetter.get(i - 1)))) {
                hashMap.put((firstLetter.get(i - 1)), postion);
                postion++;
                alepth[postion] = firstLetter.get(i);
            }
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ContentValues contentValues = mAdapter.getItem(position);
        callack.getContact(contentValues.getAsString("name"), contentValues.getAsString("number"));
        finish();
    }

    public interface OnContactCallBack {
        void getContact(String name, String phone);
    }


    public static void launch(final Context context, final OnContactCallBack listener) {
        context.startActivity(new Intent(context, SelectContactActivity.class));
        callack = listener;
    }

    public String getFirstLetter(String str) {
        Pattern pattern = Pattern.compile("^[A-Za-z]+$");
        if (str.equals("") || str == null) {
            return "#";
        }
        String s = str.substring(0, 1);
        if (pattern.matcher(s).matches()) {
            return s.toUpperCase();
        } else {
            return "#";
        }
    }

    public void showWindows() {
        LayoutInflater inflater = LayoutInflater.from(this);
        overlay = (TextView) inflater.inflate(R.layout.overlay, null, false);
        overlay.setVisibility(View.INVISIBLE);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                PixelFormat.TRANSLUCENT);
        windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        windowManager.addView(overlay, lp);

    }

    public boolean getMapValue(HashMap<String, Integer> hashMap, String key) {
        Iterator iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            if (key.equals(entry.getKey())) {
                return true;
            }
        }
        return false;
    }
}
