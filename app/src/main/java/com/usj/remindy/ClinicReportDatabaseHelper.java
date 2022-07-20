package com.usj.remindy;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class ClinicReportDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG="Database helper";

     public ClinicReportDatabaseHelper(@Nullable Context context) {
        super(context,"clinicReminder.db",null, 21);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists clinic(id integer primary key autoincrement,detail text,hospital text, doctor text,date text,time text )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists clinic");
        onCreate(db);
    }

    public void insertData(ClinicReportData data){
          SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("detail",data.getDetail());
        contentValues.put("hospital",data.getHospital());
        contentValues.put("doctor",data.getDoctor());
        contentValues.put("date",data.getDate());
        contentValues.put("time",data.getTime());
        long clinic = sqLiteDatabase.insert("clinic", null, contentValues);
        Log.e(TAG,"insertdata:"+clinic);
    }
}
