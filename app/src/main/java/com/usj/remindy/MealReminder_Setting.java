package com.usj.remindy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Locale;

public class MealReminder_Setting extends AppCompatActivity {


    String[] items =  {"Breakfast","Lunch","Dinner"};
    AutoCompleteTextView autoCompleteTxt,TimeView;
    ArrayAdapter<String> adapterItems;
    int hour, minute;
    String format;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_reminder);
        autoCompleteTxt = findViewById(R.id.auto_complete_txt2);
        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item,items);
        autoCompleteTxt.setAdapter(adapterItems);
        TimeView = findViewById(R.id.Time);
        ImageButton Back =  findViewById(R.id.back);

        TimeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popTimePicker(v);
            }
        });

        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();
            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MealReminder_Setting.this,MealReminder_intro.class);
                startActivity(i);
            }
        });


    }

    public void popTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                if (hour == 0) {

                    hour += 12;

                    format = "AM";
                }
                else if (hour == 12) {

                    format = "PM";

                }
                else if (hour > 12) {

                    hour -= 12;

                    format = "PM";

                }
                else {

                    format = "AM";
                }

                TimeView.setText(String.format(Locale.getDefault(), "%02d:%02d  %s", hour, minute,format));
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, /*style,*/ onTimeSetListener, hour, minute, false);

        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();

    }
}