package com.usj.remindy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MealReminder_intro extends AppCompatActivity {


    private Object viewId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_reminder_intro);
        ImageButton Back =  findViewById(R.id.back);
        TextView BreakfastTime =findViewById(R.id.Btxttimevalue);
        TextView LanchTime = findViewById(R.id.Ltxttimevalue);
        TextView DinnerTime = findViewById(R.id.Dtxttimevalue);

        LanchTime.setText(Meal_Remider_Gloable.Lunch[1].toString()+"."+Meal_Remider_Gloable.Lunch[2].toString()+Meal_Remider_Gloable.Lunch[3].toString());
        BreakfastTime.setText(Meal_Remider_Gloable.Breakfast[1].toString()+"."+Meal_Remider_Gloable.Breakfast[2].toString()+Meal_Remider_Gloable.Breakfast[3].toString());
        DinnerTime.setText(Meal_Remider_Gloable.Dinner[1].toString()+"."+Meal_Remider_Gloable.Dinner[2].toString()+Meal_Remider_Gloable.Dinner[3].toString());


        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MealReminder_intro.this,MainActivity.class);
                startActivity(i);
            }
        });
    }


    public void OpenSetting_Lunch(View view) {
        Intent i = new Intent(MealReminder_intro.this,MealReminder_Setting.class);
        i.putExtra("MealName",Meal_Remider_Gloable.Lunch[0].toString());
        i.putExtra("Time",Meal_Remider_Gloable.Lunch[1].toString()+"."+Meal_Remider_Gloable.Lunch[2].toString()+Meal_Remider_Gloable.Lunch[3].toString());
        startActivity(i);

    }

    public void OpenSetting_Dinner(View view) {
        Intent i = new Intent(MealReminder_intro.this,MealReminder_Setting.class);
        i.putExtra("MealName",Meal_Remider_Gloable.Dinner[0].toString());
        i.putExtra("Time",Meal_Remider_Gloable.Dinner[1].toString()+"."+Meal_Remider_Gloable.Dinner[2].toString()+Meal_Remider_Gloable.Dinner[3].toString());

        startActivity(i);

    }

    public void OpenSetting_Breakfast(View view) {
        Intent i = new Intent(MealReminder_intro.this,MealReminder_Setting.class);
        i.putExtra("MealName",Meal_Remider_Gloable.Breakfast[0].toString());
        i.putExtra("Time",Meal_Remider_Gloable.Breakfast[1].toString()+"."+Meal_Remider_Gloable.Breakfast[2].toString()+Meal_Remider_Gloable.Breakfast[3].toString());

        startActivity(i);

    }
}