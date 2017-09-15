package com.example.pc.xmnsg.view;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Desc
 * Author 程茁燕
 * Time 2017/9/1.
 */
public class RecordSQLiteOpenHelper extends SQLiteOpenHelper {

    public RecordSQLiteOpenHelper(Context context) {
        super(context, "users.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table records(id integer primary key autoincrement,name varchar(200),flag vrchar(100))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
