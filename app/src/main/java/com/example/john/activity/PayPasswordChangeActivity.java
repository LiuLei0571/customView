package com.example.john.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import com.example.john.customviewtitle.R;
import com.example.join.util.PayPasswordView;

/**
 * 用途：
 * 作者：Created by john on 2016/8/15.
 * 邮箱：liulei2@aixuedai.com
 */

public class PayPasswordChangeActivity extends AppCompatActivity {
    PayPasswordView editView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_passwrod);
        editView = (PayPasswordView) findViewById(R.id.edit_pay_password);
        editView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 5) {
                    Toast.makeText(PayPasswordChangeActivity.this, "设置完成", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
