package com.hhd.java.A_2_17_0104;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.hhd.java.R;


/**
 * A17-01 ContentProvider_简介与准备工作
 * A17-02 ContentProvider_定义匹配器与添加数据
 * A17-03 ContentProvider_ 删除修改查询方法与数据类型
 * A17-04 ContentProvider_调用内容提供者与小结
 *
 */
public class ContentProviderActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
    }

    /**
     * 增
     * @param view
     */
    public void addClick(View view){
        ContentResolver cr = this.getContentResolver();
        //content://com.hhd.java.a_2_17_0104.hellocontentprovider/person
        //content://com.hhd.java.a_2_17_0104.hellocontentprovider/person/1
        //调用CP的添加方法
        Uri uri = Uri.parse("content://com.hhd.java.a_2_17_0104.hellocontentprovider/person");
        ContentValues values = new ContentValues();
        values.put(PersonMetadata.Person.NAME,"HHD");
        values.put(PersonMetadata.Person.AGE, 18);
        cr.insert(uri, values);
    }

    /**
     * 删
     * @param view
     */
    public void deleteClick(View view){
        ContentResolver cr = this.getContentResolver();
        Uri uri = Uri.parse("content://com.hhd.java.a_2_17_0104.hellocontentprovider/person/1");
        cr.delete(uri,null,null);
    }

    /**
     * 改
     * @param view
     */
    public void updateClick(View view){
        ContentResolver cr = this.getContentResolver();
        Uri uri = Uri.parse("content://com.hhd.java.a_2_17_0104.hellocontentprovider/person/1");
        ContentValues values = new ContentValues();
        values.put(PersonMetadata.Person.NAME,"HHD-");
        values.put(PersonMetadata.Person.AGE, 20);
        cr.update(uri,values,null,null);
    }

    /**
     * 查
     * @param view
     */
    public void queryClick(View view){
        ContentResolver cr = this.getContentResolver();
        Uri uri = Uri.parse("content://com.hhd.java.a_2_17_0104.hellocontentprovider/person");
        Cursor c = cr.query(uri,null,null,null,null);
        while(c.moveToNext()){
            System.out.println(c.getInt(c.getColumnIndex(PersonMetadata.Person._ID)));
            System.out.println(c.getString(c.getColumnIndex(PersonMetadata.Person.NAME)));
            System.out.println(c.getInt(c.getColumnIndex(PersonMetadata.Person.AGE)));
        }
        c.close();
    }

}
