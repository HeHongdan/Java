package com.hhd.java.A_2_17_0104;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;

import static android.R.attr.id;

/**
 * A17-01 ContentProvider_简介与准备工作
 *
 * Created by HHJ on 2016/5/25.
 */

public class DatabaseAdapter {

    private DatabaseHelper dbHelper;

    public DatabaseAdapter(Context context){
        dbHelper = new DatabaseHelper(context);
    }

    /**
     *增
    * @param person
     */
    public void save(Person person){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PersonMetadata.Person.NAME, person.getName());
        values.put(PersonMetadata.Person.AGE, person.getAge());

        db.insert(PersonMetadata.Person.TABLE_NAME, null, values);

        db.close();
    }

    /**
     * 删
     * @param id
     */
    public void delete(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(PersonMetadata.Person.TABLE_NAME, PersonMetadata.Person._ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    /**
     * 改
     * @param person
     */
    public void update(Person person){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PersonMetadata.Person.NAME, person.getName());
        values.put(PersonMetadata.Person.AGE, person.getAge());

        String where = PersonMetadata.Person._ID + "=?";
        String[] args = {String.valueOf(person.getId())};

        db.update(PersonMetadata.Person.TABLE_NAME, values, where, args);
        db.close();
    }

    /**
     * 查
     * @return
     */
    public ArrayList<Person> findAll(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query(PersonMetadata.Person.TABLE_NAME, null, null, null,null, null, null);
        ArrayList<Person> list = new ArrayList<>();
        Person person = null;
        while (c.moveToNext()) {
            person = new Person();
            person.setId(c.getInt(c.getColumnIndex(PersonMetadata.Person._ID)));
            person.setName(c.getString(c.getColumnIndex(PersonMetadata.Person.NAME)));
            person.setAge(c.getInt(c.getColumnIndex(PersonMetadata.Person.AGE)));
            list.add(person);
        }
        c.close();
        db.close();
        return list;
    }

    /**
     * 查
     * @return
     */
    public Person findById(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query(PersonMetadata.Person.TABLE_NAME, null, PersonMetadata.Person._ID+"=?", new String[]{String.valueOf(id)},null, null, null);
        Person person = null;
        while (c.moveToNext()) {
            person = new Person();
            person.setId(c.getInt(c.getColumnIndex(PersonMetadata.Person._ID)));
            person.setName(c.getString(c.getColumnIndex(PersonMetadata.Person.NAME)));
            person.setAge(c.getInt(c.getColumnIndex(PersonMetadata.Person.AGE)));
        }
        c.close();
        db.close();
        return person;
    }

    /**
     * 数据库助手类
     */
    public static class DatabaseHelper extends SQLiteOpenHelper {

        private static final String DB_NAME = "cp.db";
        private static final int VERSION = 1;
        private static final String CREATE_TABLE = "create table person (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT, age INT)";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS person";

        public DatabaseHelper(Context context) {
            super(context, DB_NAME, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oleVersion, int newVersion) {
            db.execSQL(DB_NAME);
            db.execSQL(CREATE_TABLE);
        }
    }
}
