package com.example.john.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.john.customviewtitle.R;
import com.example.join.helper.ContactAsyncQueryHandler;

import java.util.List;

/**
 * 用途：
 * 作者：Created by john on 2016/8/3.
 * 邮箱：liulei2@aixuedai.com
 */

public class ContactActivity extends Activity {
    private EditText mPhone;
    private TextView mName;
    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        initView();
        initData();
    }

    public void initView() {
        mName = (TextView) findViewById(R.id.name);
        mPhone = (EditText) findViewById(R.id.phone);
        mBtn = (Button) findViewById(R.id.btn_phone);
    }

    public void initData() {
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactAsyncQueryHandler contactAsyncQueryHandler = new ContactAsyncQueryHandler(ContactActivity.this.getContentResolver(), true, new ContactAsyncQueryHandler.OnContactLoadingCompleteListener() {
                    @Override
                    public void onComplete(List<ContentValues> contentValues) {
                        if (contentValues.isEmpty()) {
                            Toast.makeText(ContactActivity.this, "请在设置中选择允许访问通讯录", Toast.LENGTH_LONG).show();
                            return;
                        }
                        SelectContactActivity.launch(ContactActivity.this, new SelectContactActivity.OnContactCallBack() {
                            @Override
                            public void getContact(String name, String phone) {
                                mName.setText(name);
                                phone = phone.replace("-", "").trim();
                                mPhone.setText(phone);
                                mPhone.setSelection(phone.length());
                            }
                        });
                    }
                });
                contactAsyncQueryHandler.start();
            }

        });
    }
}
