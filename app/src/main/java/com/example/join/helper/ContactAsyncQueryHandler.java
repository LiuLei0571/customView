package com.example.join.helper;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;

import com.example.join.model.Contact;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

import java.util.ArrayList;
import java.util.List;

/**
 * 用途：
 * 作者：Created by john on 2016/8/3.
 * 邮箱：liulei2@aixuedai.com
 */

public class ContactAsyncQueryHandler extends AsyncQueryHandler {
    public OnContactLoadingCompleteListener listener;
    private OnContactUploaderListener uploaderListener;
    private boolean hasContact = false;

    public ContactAsyncQueryHandler(ContentResolver cr) {
        super(cr);
    }

    public ContactAsyncQueryHandler(ContentResolver cr, Boolean hasContact, OnContactLoadingCompleteListener listener) {
        super(cr);
        this.hasContact = hasContact;
        this.listener = listener;
    }

    @Override
    protected void onQueryComplete(int token, Object cookie, Cursor cursor) {

        //用于上传通讯录
        if (uploaderListener != null) {
            if (cursor != null && cursor.getCount() > 0) {
                List<Contact> list = new ArrayList<>();
                cursor.moveToFirst();
                for (int i = 0; i < cursor.getCount(); i++) {
                    cursor.moveToPosition(i);
                    Contact contact = new Contact();
                    String name = cursor.getString(1);
                    String number = cursor.getString(2);
                    if (TextUtils.isEmpty(name) || TextUtils.isEmpty(number)) {
                        continue;
                    }
                    contact.setN(name);
                    contact.setT(number);
                    list.add(contact);
                }
                cursor.close();
                uploaderListener.onComplete(list);
            } else {
                if (cursor != null) cursor.close();
                uploaderListener.onComplete(new ArrayList<Contact>());
            }
            if (cursor != null) cursor.close();
            return;
        }

        //检查是否通讯录有权限
        if (hasContact && (cursor != null && cursor.getCount() > 0)) {
            List<ContentValues> contentValues = new ArrayList<>();
            contentValues.add(new ContentValues());
            listener.onComplete(contentValues);
            cursor.close();
            return;
        }

        //检索通讯录，分析排序数据
        if (cursor != null && cursor.getCount() > 0) {
            List<ContentValues> list = new ArrayList<>();
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToPosition(i);
                ContentValues cv = new ContentValues();
                String name = cursor.getString(1);
                String number = cursor.getString(2);
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(number)) {
                    continue;
                }
                String sortKey = getEName(name);
                cv.put("name", name);
                cv.put("number", number);
                cv.put("sort_key", sortKey);
                list.add(cv);
            }
            cursor.close();
            if (listener != null) listener.onComplete(list);
        } else {
            if (cursor != null) cursor.close();
            if (listener != null) listener.onComplete(new ArrayList<ContentValues>());
        }
    }

    /**
     * 查找联系人
     */
    public void start() {
        Uri uri = Uri.parse("content://com.android.contacts/data/phones");
        String[] projection = {"_id", "display_name", "data1", "sort_key"};
        startQuery(0, null, uri, projection, null, null,
                "sort_key COLLATE LOCALIZED asc");
    }

    public interface OnContactLoadingCompleteListener {
        void onComplete(List<ContentValues> contentValues);
    }

    public interface OnContactUploaderListener {
        void onComplete(List<Contact> contentValues);
    }

    public void setListener(OnContactLoadingCompleteListener listener) {
        this.listener = listener;
    }

    public void setContactUploaderListener(OnContactUploaderListener contactUploaderListener) {
        this.uploaderListener = contactUploaderListener;
    }

    public static String getEName(String name) {
        HanyuPinyinOutputFormat pyFormat = new HanyuPinyinOutputFormat();
        pyFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);    //设置样式
        pyFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        pyFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
        try {
            if ((name.charAt(0) > 64 && name.charAt(0) < 90) || (name.charAt(0) > 97 && name.charAt(0) < 122)) {
                return name;
            }

            String[] a = PinyinHelper.toHanyuPinyinStringArray(name.charAt(0), pyFormat);
            return (a != null && a.length > 0) ? a[0] : "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
