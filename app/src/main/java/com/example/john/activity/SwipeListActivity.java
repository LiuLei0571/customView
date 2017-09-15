package com.example.john.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.example.john.customviewtitle.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liu on 2016/4/6.
 * TODO:
 */
public class SwipeListActivity extends Activity {
    private List<String> mList=new ArrayList<String>();
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_swipelist);
        getDate();

    }
    public void getDate(){
        mList.add("密码学");
        mList.add("数据结构");
        mList.add("数据库");
        mList.add("高等数学");
        mList.add("编译原理");
        mList.add("计算机组成系统");
        mList.add("网络技术");
        mList.add("C语言");
        mList.add("Java基础");
    }
}
