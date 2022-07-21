package com.usj.remindy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Locale;

public class MedicineReminder extends AppCompatActivity {
    String[] items={"Select One","Type1","Type2","Type3","Type4"};
    int hour,minute;
    AutoCompleteTextView autoCompleteTextView;
    AutoCompleteTextView medicine,dose;
    Button timeButton,add,cancel;
    ArrayAdapter<String> adapterItems;

    private MedicineReminderDatabaseHelper medicineReminderDatabaseHelper;
    private SQLiteDatabase sqLiteDatabase;
    private int id=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_reminder);

        medicineReminderDatabaseHelper=new MedicineReminderDatabaseHelper(getApplicationContext());

        //defining components
        add=findViewById(R.id.save);
        cancel=findViewById(R.id.cancel);
        timeButton=findViewById(R.id.timeButton);
        autoCompleteTextView=findViewById(R.id.ettype);
        medicine=findViewById(R.id.etmedicine);
        dose=findViewById(R.id.etdose);


        adapterItems= new ArrayAdapter<String>(this,R.layout.list_item,items);
        autoCompleteTextView.setAdapter(adapterItems);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item=adapterView.getItemAtPosition(i).toString();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(autoCompleteTextView.getText().toString().matches("")||medicine.getText().toString().matches("")||dose.getText().toString().matches("")||timeButton.getText().toString().matches("Select Time")){
                    Toast.makeText(getApplicationContext(),"All the fields are mandatory to fill",Toast.LENGTH_LONG).show();
                }
                else{
                    ContentValues contentValues=new ContentValues();
                    contentValues.put("type",autoCompleteTextView.getText().toString());
                    contentValues.put("medicine",medicine.getText().toString());
                    contentValues.put("dose",dose.getText().toString());
                    contentValues.put("time",timeButton.getText().toString());

                    sqLiteDatabase=medicineReminderDatabaseHelper.getWritableDatabase();
                    Long recinsert=sqLiteDatabase.insert("medicine",null,contentValues);
                    if(recinsert!=null){


                        Toast.makeText(getApplicationContext(),"New Medicine Added",Toast.LENGTH_LONG).show();

                        //Log.i("minute",minute);
                        //Log.i("hour",hour);

                        autoCompleteTextView.setText("");
                        medicine.setText("");
                        dose.setText("");
                        timeButton.setText("Select Time");
                        //Log.e(TAG,"insertdata:"+recinsert);
                    }else{
                        Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });



    }


    public void popTimePicker (View view){
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                timeButton.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));


            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, /*style,*/ onTimeSetListener, hour, minute, true);

        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }
}