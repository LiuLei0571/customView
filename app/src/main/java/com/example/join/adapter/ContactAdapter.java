package com.example.join.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.john.activity.SelectContactActivity;
import com.example.john.customviewtitle.R;

import java.util.List;
import java.util.regex.Pattern;

/**
 * 用途：
 * 作者：Created by john on 2016/8/4.
 * 邮箱：liulei2@aixuedai.com
 */

public class ContactAdapter extends BaseAdapter {
    private List<ContentValues> contentValues;
    private Context context;

    public ContactAdapter(List<ContentValues> contentValues, Context context) {
        this.contentValues = contentValues;
        this.context = context;
    }

    public ContactAdapter(Context context) {
        this.context = context;
    }

    public void setContentValues(List<ContentValues> contentValue) {
        this.contentValues.clear();
        this.contentValues.addAll(contentValue);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return contentValues.size() > 0 ? contentValues.size() : 0;
    }

    @Override
    public ContentValues getItem(int position) {
        return contentValues.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ContactViewHolder contactViewHolder;
        ContentValues contentValues = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_contact, null);
            contactViewHolder = new ContactViewHolder();
            contactViewHolder.title = (TextView) convertView.findViewById(R.id.title);
            contactViewHolder.name = (TextView) convertView.findViewById(R.id.name);
            convertView.setTag(contactViewHolder);
        } else {
            contactViewHolder = (ContactViewHolder) convertView.getTag();
        }
        contactViewHolder.title.setText(getFirstLetter(contentValues.getAsString("sort_key")));
        contactViewHolder.name.setText(contentValues.getAsString("name"));
        String preLetter = position - 1 >= 0 ? getFirstLetter(getItem(position - 1).getAsString("sort_key")) : "";
        if (preLetter.equals(contactViewHolder.title.getText().toString())) {
            contactViewHolder.title.setVisibility(View.GONE);
        } else {
            contactViewHolder.title.setVisibility(View.VISIBLE);
        }
        return convertView;
    }

    public class ContactViewHolder {
        TextView title;
        TextView name;
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
}
