package com.usj.remindy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ClinicReportReminder extends AppCompatActivity {

    Button timeButton;
    int hour, minute;
    int year,month,day;
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private Button add;
    private Button cancel;
    private EditText detail;
    private EditText hospital;
    private EditText doctor;

    private ClinicReportDatabaseHelper clinicReportDatabaseHelper;
    private SQLiteDatabase sqLiteDatabase;
    private int id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_report_reminder);

        clinicReportDatabaseHelper = new ClinicReportDatabaseHelper(getApplicationContext());

        //Component Definition
        timeButton = findViewById(R.id.timeButton);
        dateButton = findViewById(R.id.datePickerButton);
        detail=findViewById(R.id.Detail);
        hospital=findViewById(R.id.Hospital);
        doctor=findViewById(R.id.Doctor);
        add=findViewById(R.id.save);
        cancel=findViewById(R.id.cancel);


        initDatePicker();

        dateButton.setText(getTodaysDate());

        editData();

        //save data to SQLite database
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(detail.getText().toString().matches("")||hospital.getText().toString().matches("")||doctor.getText().toString().matches("")||timeButton.getText().toString()=="Select Time"){
                    Toast.makeText(ClinicReportReminder.this,"All the fields are mandatory to fill",Toast.LENGTH_LONG).show();
                }
                else{
                    ContentValues contentValues=new ContentValues();
                    contentValues.put("detail",detail.getText().toString());
                    contentValues.put("hospital",hospital.getText().toString());
                    contentValues.put("doctor",doctor.getText().toString());
                    contentValues.put("date",dateButton.getText().toString());
                    contentValues.put("time",timeButton.getText().toString());

                    sqLiteDatabase=clinicReportDatabaseHelper.getWritableDatabase();
                    Long recinsert=sqLiteDatabase.insert("clinic",null,contentValues);

                    NotificationChannel();
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY, hour);
                    calendar.set(Calendar.MINUTE, minute);
                    calendar.set(Calendar.SECOND, 00);
                    if (Calendar.getInstance().after(calendar)) {
//
                        calendar.add(Calendar.DAY_OF_MONTH , day) ;
                        calendar.add(Calendar.MONTH,month);
                        calendar.add(Calendar.YEAR,year);
                    }

                    Intent intent = new Intent(ClinicReportReminder.this, MemoBroadcast.class);
                    Bundle b = new Bundle();
                    b.putString("Title","Clinic Reminder");
                    b.putString("Desc","Today Your Have Appointment");
                    intent.putExtra("Details",b);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
                    }




                    if(recinsert!=null){


                        Toast.makeText(ClinicReportReminder.this,"New Reminder Added",Toast.LENGTH_LONG).show();

                        //Log.i("minute",minute);
                        //Log.i("hour",hour);

                        detail.setText("");
                        hospital.setText("");
                        doctor.setText("");
                        dateButton.setText("Select Date");
                        timeButton.setText("Select Time");
                        //Log.e(TAG,"insertdata:"+recinsert);
                    }else{
                        Toast.makeText(ClinicReportReminder.this,"Something went wrong",Toast.LENGTH_LONG).show();
                    }


                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ClinicReportReminder.this,AddNewClinicData.class);
                startActivity(i);
            }
        });
    }

    private void NotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "REMINDY Clinic & Report";
            String description = "REMINDY`S CHANNEL";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("Notification Clinic", name, importance);
            channel.setDescription(description);


            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);


        }
    }

    private void editData() {
        if(getIntent().getBundleExtra("clinicdata")!=null){
            Bundle bundle=getIntent().getBundleExtra("clinicdata");
            id=bundle.getInt("id");
            detail.setText(bundle.getString("detail"));
            hospital.setText(bundle.getString("hospital"));
            doctor.setText(bundle.getString("doctor"));
            dateButton.setText(bundle.getString("date"));
            timeButton.setText(bundle.getString("time"));
        }
    }


    public void popTimePicker (View view){
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                timeButton.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));

                //for alarm
                //Intent alar=new Intent(AlarmClock.ACTION_SET_ALARM);
                //alar.putExtra(AlarmClock.EXTRA_HOUR,selectedHour);
               // alar.putExtra(AlarmClock.EXTRA_MINUTES,selectedMinute);
                //Calendar cale= Calendar.getInstance();
                //String today= DateFormat.getDateInstance(DateFormat.MEDIUM).format(cale.getTime());


               // startActivity(alar);
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, /*style,*/ onTimeSetListener, hour, minute, true);

        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }
    //change to public
    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };
        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + ", " + year;
    }

    private String getMonthFormat(int month) {
        if(month == 1)
            return "Jan";
        if(month == 2)
            return "Feb";
        if(month == 3)
            return "Mar";
        if(month == 4)
            return "Apr";
        if(month == 5)
            return "May";
        if(month == 6)
            return "Jun";
        if(month == 7)
            return "Jul";
        if(month == 8)
            return "Aug";
        if(month == 9)
            return "Sep";
        if(month == 10)
            return "Oct";
        if(month == 11)
            return "Nov";
        if(month == 12)
            return "Dec";

        //default should never happen
        return "Jan";
    }




        public void openDatePicker (View view){
            datePickerDialog.show();
        }
    public void Goback(View view) {
        Intent i = new Intent(ClinicReportReminder.this,MainActivity.class);
        startActivity(i);
    }
}

