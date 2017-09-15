package com.example.john.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.join.util.CustomTitleView;
import com.example.john.customviewtitle.R;

public class VerificationActivity extends Activity {

    private EditText mEt;
    private Button mBtn;
    private CustomTitleView mCustomView;
    private String number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        init();
    }
    private void init(){
        mEt=(EditText)this.findViewById(R.id.et_number);
        mBtn=(Button)this.findViewById(R.id.btn_verification);
        mCustomView=(CustomTitleView)this.findViewById(R.id.tv_custom);
        mCustomView.setmBack(new CustomTitleView.CallBack() {
            @Override
            public void onCBack(String numbers) {
                number=numbers;
            }
        });
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mEt.getText().toString().equals(number)){
                    Toast.makeText(VerificationActivity.this,"验证通过！",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(VerificationActivity.this,"验证失败！",Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}
