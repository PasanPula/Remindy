package com.usj.remindy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

public class AddNewWaterReminder extends AppCompatActivity {

    String[] items =  {"200ml", "300ml", "400ml", "500ml", "600ml", "700ml"};
    AutoCompleteTextView autoCompleteTxt,TimePick,DatePick;
    ArrayAdapter<String> adapterItems;
    int hour, minute, year,month,day;
    int Ab_hour,Ab_minute;
    String format;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_water_reminder);

        autoCompleteTxt = findViewById(R.id.Waterauto_complete_txt2);
        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item,items);
        autoCompleteTxt.setAdapter(adapterItems);
        TimePick = findViewById(R.id.WaterTime);
        DatePick = findViewById(R.id.WaterDate);

        TimePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popTimePicker(v);
            }
        });

        DatePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popDatePicker(v);
            }                                        //when we click on the choose date button it calls the select date method
        });

        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();
            }
        });
    }



    public void popTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                Ab_hour=selectedHour;
                Ab_minute=selectedMinute;

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

                TimePick.setText(String.format(Locale.getDefault(), "%02d:%02d  %s", hour, minute,format));
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, /*style,*/ onTimeSetListener, hour, minute, false);

        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();

    }


    private void popDatePicker(View v) {                                                                     //this method performs the date picker task
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                DatePick.setText(day + "-" + (month + 1) + "-" + year);                             //sets the selected date as test for button
            }
        }, year, month, day);
        datePickerDialog.show();
    }




    public void createNotification (View view) {
        Intent myIntent = new Intent(getApplicationContext() , NotificationService.class ) ;
        AlarmManager alarmManager = (AlarmManager) getSystemService( ALARM_SERVICE ) ;
        PendingIntent pendingIntent = PendingIntent. getService ( this, 0 , myIntent , 0 ) ;

        Calendar calendar = Calendar.getInstance () ;
        calendar.set(Calendar.SECOND , 0 ) ;
        calendar.set(Calendar.MINUTE , 0 ) ;
        calendar.set(Calendar.HOUR , 0 ) ;
        calendar.set(Calendar.AM_PM , Calendar.AM ) ;
        calendar.add(Calendar.DAY_OF_MONTH , 1 ) ;
        alarmManager.setRepeating(AlarmManager. RTC_WAKEUP , calendar.getTimeInMillis() , 1000 * 60 * 60 * 24 , pendingIntent) ;
    }
}