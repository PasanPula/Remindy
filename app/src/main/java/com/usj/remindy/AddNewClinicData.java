package com.usj.remindy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AddNewClinicData extends AppCompatActivity {
    ClinicReportDatabaseHelper clinicReportDatabaseHelper;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView recyclerView;
    ClinicAdapter clinicAdapter;

    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_clinic_data);


        fab =findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(AddNewClinicData.this,ClinicReportReminder.class);
                startActivity(i);
            }
        });

        clinicReportDatabaseHelper=new ClinicReportDatabaseHelper(this);
        //create method
        findid();
        displayData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void displayData() {
        sqLiteDatabase=clinicReportDatabaseHelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from clinic ",null);
        ArrayList<ClinicDataModel>modelArrayList=new ArrayList<>();
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String detail=cursor.getString(1);
            String hospital=cursor.getString(2);
            String doctor=cursor.getString(3);
            String date=cursor.getString(4);
            String time=cursor.getString(5);
            modelArrayList.add(new ClinicDataModel(id,detail,hospital,doctor,date,time));
        }
        cursor.close();
        clinicAdapter=new ClinicAdapter(this,R.layout.clinicdatasingle,modelArrayList,sqLiteDatabase);
        recyclerView.setAdapter(clinicAdapter);
    }

    private void findid() {
        recyclerView=findViewById(R.id.rv);
    }
}