package com.usj.remindy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Calendar;

public class AddNewExpireDateReminder extends AppCompatActivity {

    AutoCompleteTextView ItemName,ItemDesc,ExpDate;
    int year,month,day;
    Button AddRemind;
    private SQLiteDatabase sqLiteDatabase;
    private ExpireDateDatabaseHelper expireDateDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_expire_date_reminder);

        ImageButton back = findViewById(R.id.back);
        ExpireDateDatabaseHelper ExDb = new ExpireDateDatabaseHelper(getApplicationContext());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              btnCancle(v);
            }
        });



        ItemName = findViewById(R.id.ExName);
        ItemDesc = findViewById(R.id.ExpiryDesc);
        ExpDate = findViewById(R.id.NewExDate);
        AddRemind = findViewById(R.id.ExSubmit);

        ExpDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popDatePicker(v);
            }
        });

        AddRemind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ItemName.getText().toString().matches("")||ItemDesc.getText().toString().matches("")||ExpDate.getText().toString()=="Select Time"){
                    Toast.makeText(AddNewExpireDateReminder.this,"All the fields are mandatory to fill",Toast.LENGTH_LONG).show();
                }
                else
                {
                    ContentValues contentValues=new ContentValues();
                    contentValues.put("name",ItemName.getText().toString());
                    contentValues.put("descr",ItemDesc.getText().toString());
                    contentValues.put("date",ExpDate.getText().toString());

                    sqLiteDatabase=expireDateDatabaseHelper.getWritableDatabase();
                    Long recinsert=sqLiteDatabase.insert("expire",null,contentValues);

                    if(recinsert!=null){
                        Toast.makeText(AddNewExpireDateReminder.this,"New Reminder Added",Toast.LENGTH_LONG).show();

                        ItemName.setText("");
                        ItemDesc.setText("");
                        ExpDate.setText("Select Date");
                    }
                }
            }
        });


    }

    public void btnCancle(View view) {
        Intent inten = new Intent(AddNewExpireDateReminder.this,ExpiryDateReminder.class);
       startActivity(inten);
    }


    private void popDatePicker(View v) {                                                                     //this method performs the date picker task
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                ExpDate.setText(day + "-" + (month + 1) + "-" + year);                             //sets the selected date as test for button
            }
        }, year, month, day);
        datePickerDialog.show();
    }





}