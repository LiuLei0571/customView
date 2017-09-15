package com.example.join.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.john.customviewtitle.R;
import com.example.join.adapter.CommonRecycltAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 用途：
 * 作者：Created by john on 2016/8/16.
 * 邮箱：liulei2@aixuedai.com
 */

public class MyFragment extends Fragment {
    private String title;
    private RecyclerView recyclerView;
    private List<String> mData = new ArrayList<>();
    private CommonRecycltAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString("title");
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_my, container, false);
        initView(view);
        return view;
    }

    public void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle);
        for (int i = 0; i < 50; i++) {
            mData.add(title + i);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new CommonRecycltAdapter(getContext(), mData);
        recyclerView.setAdapter(mAdapter);
    }

    public static MyFragment newInstance(String title) {
        MyFragment tabFragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        tabFragment.setArguments(bundle);
        return tabFragment;
    }
}
