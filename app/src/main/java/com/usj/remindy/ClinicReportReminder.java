package com.usj.remindy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

public class ClinicReportReminder extends AppCompatActivity {

    Button timeButton;
    int hour, minute;
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private Button add;
    private Button cancel;
    private EditText detail;
    private EditText hospital;
    private EditText doctor;

    private ClinicReportDatabaseHelper clinicReportDatabaseHelper;

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

        //save data to SQLite database
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(detail.getText().toString().matches("")||hospital.getText().toString().matches("")||doctor.getText().toString().matches("")||timeButton.getText().toString()=="Select Time"){
                    Toast.makeText(ClinicReportReminder.this,"All the fields are mandatory to fill",Toast.LENGTH_LONG).show();
                }
                else{
                    clinicReportDatabaseHelper.insertData(new ClinicReportData(detail.getText().toString(),hospital.getText().toString(),doctor.getText().toString(),timeButton.getText().toString(),dateButton.getText().toString()));
                    Toast.makeText(ClinicReportReminder.this,"New Reminder Added",Toast.LENGTH_LONG).show();
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

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }
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
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }




        public void openDatePicker (View view){
            datePickerDialog.show();
        }

}

