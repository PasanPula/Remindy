package com.usj.remindy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MedicineReminderDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG="Database helper";

    public MedicineReminderDatabaseHelper(@Nullable Context context) {
        super(context, "medicineReminder.db", null, 21);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists medicine(id integer primary key autoincrement,type text,medicine text, dose text,time text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists medicine");
        onCreate(db);
    }
}
