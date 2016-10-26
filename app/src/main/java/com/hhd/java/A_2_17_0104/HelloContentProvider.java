package com.hhd.java.A_2_17_0104;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;


/**
 * A17-01 ContentProvider_简介与准备工作
 *
 * 自定义ContentProvider：
 * 1、创建继承ContentProvider类
 * 2、实现6个方法(Create+getType+增删改查)
 * 3、创建主机访问路径(权限)
 * 4、Uri匹配器
 * 5、CursorQuery(过滤匹配码)
 * 6、创建(数据库)操作类
 * 7、添加权限(清单文件)
 * 8、ContentResolver(解析器)访问
 *
 * 9、调用各个操作方法（增(insert)删(delete)改(update)查(query)）
 * 10、close(关闭)
 */

public class HelloContentProvider extends ContentProvider {
    //权限（URI）
    private static final String AUTHORITY = "com.hhd.java.a_2_17_0104.hellocontentprovider";
    //创建一个URI的匹配器
    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);//NO_MATCH：表示不匹配任何路径的返回码（-1）
    private static final int MUTIPLE_CODE = 1;//返回（多个的）记录的匹配码
    private static final int SINGLE_CODE = 2;////返回（单个的）记录的匹配码

    static {
        //相当于：//content://com.hhd.java.a_2_17_0104.hellocontentprovider/person/
        uriMatcher.addURI(AUTHORITY, "person", MUTIPLE_CODE);//1：返回匹配码（可以自定义）
        //相当于：//content://com.hhd.java.a_2_17_0104.hellocontentprovider/person/2
        uriMatcher.addURI(AUTHORITY, "person/#", SINGLE_CODE);//#：通配符（表示匹配所有数字）
    }

    //返回匹配类型（如：//text/plain  image/jpg）
    private static final String MUTIPLE_TYPE = "vnd.android.cursor.dir/person";//vnd.android.cursor.dir：Google默认使用，但可以自定义
    private static final String SINGLE_TYPE = "vnd.android.cursor.item/person";

    private DatabaseAdapter.DatabaseHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = new DatabaseAdapter.DatabaseHelper(getContext());//传上下文
        return true;//创建数据库成功返回true
    }

    /**
     * 判断Uri类型
     * <p>
     * 满足条件才返回
     *
     * @param uri
     * @return
     */
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case MUTIPLE_CODE://满足条件才返回
                return MUTIPLE_TYPE;
            case SINGLE_CODE:
                return SINGLE_TYPE;
        }
        return null;
    }


    /**
     * 查
     *
     * @param uri
     * @param projection
     * @param selection
     * @param selectionArgs
     * @param sortOrder
     * @return
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        switch (uriMatcher.match(uri)) {
            case SINGLE_CODE://查询一条
                SQLiteDatabase db = dbHelper.getReadableDatabase();//
                long id = ContentUris.parseId(uri);
                selection = PersonMetadata.Person._ID + "=?";
                selectionArgs = new String[]{String.valueOf(id)};
                return db.query(PersonMetadata.Person.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
            case MUTIPLE_CODE://查询所有
                db = dbHelper.getReadableDatabase();//
                return db.query(PersonMetadata.Person.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
        }
        return null;
    }

    /**
     * 增
     *
     * @param uri
     * @param values
     * @return
     */
    //content://com.hhd.java.a_2_17_0104.hellocontentprovider/person/
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        //contentValues：ContentProviderActivity-> addClick-> cr.insert(uri, values);传值过来
        switch (uriMatcher.match(uri)) {
            case MUTIPLE_CODE://因为id自动增长所以只匹配MUTIPLE_CODE
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                long id = db.insert(PersonMetadata.Person.TABLE_NAME, null, values);
                uri = ContentUris.withAppendedId(uri, id);//ContentUris：拼接 Uri工具
                db.close();
                break;
        }
        return uri;
    }

    /**
     * 删
     *
     * @param uri
     * @param selection
     * @param selectionArgs
     * @return
     */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        switch (uriMatcher.match(uri)) {
            //content://com.hhd.java.a_2_17_0104.hellocontentprovider/person/1
            case SINGLE_CODE:
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                long id = ContentUris.parseId(uri);
                selection = PersonMetadata.Person._ID + "=?";
                selectionArgs = new String[]{String.valueOf(id)};
                int row = db.delete(PersonMetadata.Person.TABLE_NAME, selection, selectionArgs);
                db.close();
                return row;
            case MUTIPLE_CODE:
                db = dbHelper.getWritableDatabase();
                row = db.delete(PersonMetadata.Person.TABLE_NAME, selection, selectionArgs);
                db.close();
                return row;
        }
        return 0;
    }

    /**
     * 更新
     *
     * @param uri
     * @param values
     * @param selection
     * @param selectionArgs
     * @return
     */
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        switch (uriMatcher.match(uri)) {
            //content://com.hhd.java.a_2_17_0104.hellocontentprovider/person/1
            case SINGLE_CODE:
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                long id = ContentUris.parseId(uri);
                selection = PersonMetadata.Person._ID + "=?";
                selectionArgs = new String[]{String.valueOf(id)};
                int row = db.update(PersonMetadata.Person.TABLE_NAME, values, selection, selectionArgs);
                db.close();
                return row;
            case MUTIPLE_CODE:
                db = dbHelper.getWritableDatabase();
                row = db.update(PersonMetadata.Person.TABLE_NAME, values, selection, selectionArgs);
                db.close();
                return row;
        }
        return 0;
    }
}
