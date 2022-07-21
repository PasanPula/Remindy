package com.usj.remindy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ExpireDateDatabaseHelper extends SQLiteOpenHelper {

    private static String dbname = "ExpireDate";

    public ExpireDateDatabaseHelper(@Nullable Context context)
    {
        super(context,"ExpireDate.db",null, 21);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "create table if not exists tbl_ExpireDate(id integer primary key autoincrement,ItemName text,desciption text,date text)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String query = "DROP TABLE IF EXISTS tbl_ExpireDate";                                         //sql query to check table with the same name or not
        sqLiteDatabase.execSQL(query);                                                              //executes the sql command
        onCreate(sqLiteDatabase);
    }





    public String addreminder(String name, String description, String date) {
        SQLiteDatabase database = this.getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("ItemName", name);                                                          //Inserts  data into sqllite database
        contentValues.put("desciption", description);
        contentValues.put("date", date);

        float result = database.insert("tbl_ExpireDate", null, contentValues);    //returns -1 if data successfully inserts into database

        if (result == -1) {
            return "Failed";
        } else {
            return "Successfully inserted";
        }

    }


    public Cursor readallreminders() {
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "select * from tbl_ExpireDate order by id desc";                               //Sql query to  retrieve  data from the database
        Cursor cursor = database.rawQuery(query, null);
        return cursor;
    }


}
