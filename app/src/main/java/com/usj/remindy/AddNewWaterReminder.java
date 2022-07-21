package com.usj.remindy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import java.util.Calendar;

public class AddNewWaterReminder extends AppCompatActivity {

    String[] items =  {"200ml", "300ml", "400ml", "500ml", "600ml", "700ml"};
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_water_reminder);

        autoCompleteTxt = findViewById(R.id.Waterauto_complete_txt2);
        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item,items);
        autoCompleteTxt.setAdapter(adapterItems);

        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();
            }
        });
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