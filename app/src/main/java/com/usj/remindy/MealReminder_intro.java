package com.usj.remindy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MealReminder_intro extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_reminder_intro);
        CardView cardBreakfast = findViewById(R.id.cardBreakfast);
        cardBreakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MealReminder_intro.this,MealReminder_Setting.class);
                startActivity(i);
            }
        });
    }

}