package com.usj.remindy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

public class MealReminder_Setting extends AppCompatActivity {


    String[] items =  {"Breakfast","Lunch","Dinner"};
    AutoCompleteTextView autoCompleteTxt,TimeView;
    ArrayAdapter<String> adapterItems;
    int hour, minute,Posision;
    private String Seleteditem;
    int Ab_hour,Ab_minute;
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
        Button Submit = findViewById(R.id.Submit);

        Intent i = getIntent();

//         autoCompleteTxt.setListSelection(0);
        Seleteditem = i.getStringExtra("MealName");
         autoCompleteTxt.setText(Seleteditem);
         TimeView.setText(i.getStringExtra("Time"));



        TimeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popTimePicker(v);
            }
        });

//        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Posision = position;
//                Toast.makeText(getApplicationContext(),"Position: "+position,Toast.LENGTH_SHORT).show();
//
//                Seleteditem = parent.getItemAtPosition(position).toString();
////                Toast.makeText(getApplicationContext(),"Selected: "+Seleteditem,Toast.LENGTH_SHORT).show();
//            }
//        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Goback(v);
            }
        });

//        Code for Submit and Notification
         Submit.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 NotificationChannel();

                 Calendar calendar = Calendar.getInstance();
                 calendar.set(Calendar.HOUR_OF_DAY, Ab_hour);
                 calendar.set(Calendar.MINUTE, Ab_minute);
                 calendar.set(Calendar.SECOND, 00);

//                 if( Seleteditem == "Lunch")
//                 {
//                     Meal_Remider_Gloable.Lunch[1] = hour;
//                     Meal_Remider_Gloable.Lunch[2] = minute;
//                     Meal_Remider_Gloable.Lunch[3] = format;
//                     Toast.makeText(getApplicationContext(),"Successfully Setup The Remider",Toast.LENGTH_SHORT).show();
//                 }
//                 if( Seleteditem == "Dinner")
//                 {
//                     Meal_Remider_Gloable.Dinner[1] = hour;
//                     Meal_Remider_Gloable.Dinner[2] = minute;
//                     Meal_Remider_Gloable.Dinner[3] = format;
//                     Toast.makeText(getApplicationContext(),"Successfully Setup The Remider",Toast.LENGTH_SHORT).show();
//                 }
//                 if( Seleteditem=="Breakfast")
//                 {
//                     Meal_Remider_Gloable.Breakfast[1] = hour;
//                     Meal_Remider_Gloable.Breakfast[2] = minute;
//                     Meal_Remider_Gloable.Breakfast[3] = format;
//                     Toast.makeText(getApplicationContext(),"Successfully Setup The Remider",Toast.LENGTH_SHORT).show();
//
//                 }

                 if (Calendar.getInstance().after(calendar)) {
                     calendar.add(Calendar.DAY_OF_MONTH, 1);
                 }

                 Intent intent = new Intent(MealReminder_Setting.this, MemoBroadcast.class);
                 Bundle b = new Bundle();
                 b.putString("Title","MealReminder");
                 b.putString("Desc","It's Time To Get Your "+Seleteditem);
                 intent.putExtra("Details",b);
                 PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                 AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                 alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
                 if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                     alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
                 }

                 Toast.makeText(getApplicationContext(),"Successfully Setup The Remider",Toast.LENGTH_SHORT).show();
             }
         });



    }


    public void Goback( View v)
    {
        Intent i = new Intent(MealReminder_Setting.this,MealReminder_intro.class);
        startActivity(i);
    }

//Notification Code
    private void NotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "REMINDY Meal";
            String description = "REMINDY`S CHANNEL";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("Notification Meal", name, importance);
            channel.setDescription(description);


            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);


        }
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

                TimeView.setText(String.format(Locale.getDefault(), "%02d:%02d  %s", hour, minute,format));
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, /*style,*/ onTimeSetListener, hour, minute, false);

        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();

    }
}