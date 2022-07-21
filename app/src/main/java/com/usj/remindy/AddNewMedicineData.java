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

public class AddNewMedicineData extends AppCompatActivity {

    MedicineReminderDatabaseHelper medicineReminderDatabaseHelper;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView recyclerView;
    MedicineAdapter medicineAdapter;

    private Button fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_medicine_reminder);

        fab=findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MedicineReminder.class);
                startActivity(i);


            }
        });
        medicineReminderDatabaseHelper=new MedicineReminderDatabaseHelper(this);
        //create method
        findid();
        displayData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void displayData() {
        sqLiteDatabase=medicineReminderDatabaseHelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from medicine ",null);
        ArrayList<MedicineDataModel> modelArrayList=new ArrayList<>();
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String type=cursor.getString(1);
            String name=cursor.getString(2);
            String dose=cursor.getString(3);
            String time=cursor.getString(4);
            modelArrayList.add(new MedicineDataModel(id,type,name,dose,time));
        }
        cursor.close();
        medicineAdapter=new MedicineAdapter(this,R.layout.medicinedatasingle,modelArrayList,sqLiteDatabase);
        recyclerView.setAdapter(medicineAdapter);
    }
    private void findid() {
        recyclerView=findViewById(R.id.rv);
    }

}