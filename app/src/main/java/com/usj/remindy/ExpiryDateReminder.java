package com.usj.remindy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class ExpiryDateReminder extends AppCompatActivity {

    LinearLayoutManager lm;
    List<ExpiryItemModel>explist;


    ExpireDateDatabaseHelper expireDateDatabaseHelper;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView recyclerView;
    ExpiryItemAdapter expiryItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expiry_date_reminder);

        Button Addbtn=findViewById(R.id.addExReminder);
        Addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ExpiryDateReminder.this,AddNewExpireDateReminder.class);
                startActivity(i);
            }
        });

        expireDateDatabaseHelper=new ExpireDateDatabaseHelper(this);
        findid();
        displayData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    private void displayData() {
        sqLiteDatabase=expireDateDatabaseHelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from clinic ",null);
        ArrayList<ExpiryItemModel>modelArrayList=new ArrayList<>();
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String Name=cursor.getString(1);
            String Description=cursor.getString(2);
            String Date=cursor.getString(3);

            modelArrayList.add(new ExpiryItemModel(id,Name,Description,Date));
        }
        cursor.close();
        expiryItemAdapter=new ExpiryItemAdapter(this,R.layout.exp_item_design,modelArrayList,sqLiteDatabase);
        recyclerView.setAdapter(expiryItemAdapter);


    }

    private void findid() {
        recyclerView=findViewById(R.id.addExRV);
    }
}