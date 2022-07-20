package com.usj.remindy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

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
    }
}