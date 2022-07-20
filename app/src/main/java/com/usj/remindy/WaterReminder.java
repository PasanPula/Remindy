package com.usj.remindy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.Toast;

public class WaterReminder extends AppCompatActivity {

    private int CurrentProgress = 0;
    private ProgressBar progressBar;
    private Button StartProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_reminder);

        progressBar = findViewById(R.id.progressBar);
        StartProgress = findViewById(R.id.startProgess);

        final CountDownTimer countDownTimer = new CountDownTimer(11 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                CurrentProgress = CurrentProgress + 10;
                progressBar.setProgress(CurrentProgress);
                progressBar.setMax(100);

            }

            @Override
            public void onFinish() {

            }
        };

        StartProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                countDownTimer.start();
            }
        });


        NumberPicker numberPicker = findViewById(R.id.numberPicker);
        if (numberPicker != null) {
            final String[] values = {"200ml", "300ml", "400ml", "500ml", "600ml", "700ml"};
//            numberPicker.setMinValue(0);
            numberPicker.setValue(200);
            numberPicker.setWrapSelectorWheel(true);
            numberPicker.setMaxValue(values.length - 1);
            numberPicker.setDisplayedValues(values);
            numberPicker.setWrapSelectorWheel(true);
            numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    String text = "Changed from " + values[oldVal] + " to " + values[newVal];
                    Toast.makeText(WaterReminder.this, text, Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}