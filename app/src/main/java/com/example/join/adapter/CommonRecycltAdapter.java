package com.example.join.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.john.customviewtitle.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 用途：
 * 作者：Created by john on 2016/8/16.
 * 邮箱：liulei2@aixuedai.com
 */

public class CommonRecycltAdapter extends RecyclerView.Adapter<CommonRecycltAdapter.MyViewHolder> {

    private Context context;
    private List<String> mList = new ArrayList<>();

    public CommonRecycltAdapter(Context context, List<String> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (holder != null) {
         holder.tv.setText(mList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.id_info);
        }
    }
}
