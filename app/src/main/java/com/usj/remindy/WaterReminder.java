package com.usj.remindy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class WaterReminder extends AppCompatActivity {

    private int CurrentProgress = 0;
    private ProgressBar progressBar;
    private Button StartProgress;
    private int level = 0;
    private int drinkCount=0;
    private TextView drinklv;
    final String[] values = {"200ml", "300ml", "400ml", "500ml", "600ml", "700ml"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_reminder);

        progressBar = findViewById(R.id.progressBar);
        StartProgress = findViewById(R.id.startProgess);
        drinklv = findViewById(R.id.txtDrinkLV);


        StartProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                CurrentProgress = CurrentProgress + 10;
                progressBar.setProgress(CurrentProgress);

                if(progressBar.getProgress() == 100)
                {
                    progressBar.setProgress(0);
                    CurrentProgress =0;
                }


                if(level==0)
                {
                    drinkCount = drinkCount+200;
                    CurrentProgress = CurrentProgress + 3;
                } else if(level==1)
                {
                    drinkCount = drinkCount+300;
                    CurrentProgress = CurrentProgress + 4;
                }else if(level==2)
                {
                    drinkCount = drinkCount+400;
                    CurrentProgress = CurrentProgress + 6;
                }else if(level==3)
                {
                    drinkCount = drinkCount+500;
                    CurrentProgress = CurrentProgress + 7;
                }else if(level==4)
                {
                    drinkCount = drinkCount+600;
                    CurrentProgress = CurrentProgress + 9;
                }
                else if(level==5)
                {
                    drinkCount = drinkCount+700;
                    CurrentProgress = CurrentProgress + 10;
                }
                else{
                    drinkCount = 0;
                }

                if(drinkCount > 6500)
                {
                    drinkCount = 0;
                    progressBar.setProgress(100);
                    Toast.makeText(getApplicationContext(),"Congratualtions,You achieved daily Target",Toast.LENGTH_SHORT).show();
                }

                drinklv.setText(String.valueOf(drinkCount));

            }
        });


        NumberPicker numberPicker = findViewById(R.id.numberPicker);
        if (numberPicker != null) {

//            numberPicker.setMinValue(0);
            numberPicker.setValue(200);
            numberPicker.setWrapSelectorWheel(true);
            numberPicker.setMaxValue(values.length - 1);
            numberPicker.setDisplayedValues(values);
            numberPicker.setWrapSelectorWheel(true);
            numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    level = newVal;
                }
            });
        }



        Button Addbtn=findViewById(R.id.addWaterReminder);
        Addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(WaterReminder.this,AddNewWaterReminder.class);
                startActivity(i);
            }
        });

    }
}