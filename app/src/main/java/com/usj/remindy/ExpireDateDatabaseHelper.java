package com.usj.remindy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ExpireDateDatabaseHelper extends SQLiteOpenHelper {


    public ExpireDateDatabaseHelper(@Nullable Context context) {
        super(context,"expiredate.db", null, 21);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists expire(id integer primary key autoincrement,name text,descr text, date text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists expire");
        onCreate(db);
    }
}