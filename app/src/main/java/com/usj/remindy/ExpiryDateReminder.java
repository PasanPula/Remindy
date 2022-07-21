package com.usj.remindy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ExpiryDateReminder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expiry_date_reminder);

        Button Addbtn=findViewById(R.id.addExReminder);
        Addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ExpiryDateReminder.this,AddNewExpireDateReminder.class);
                startActivity(i);
            }
        });
    }


}