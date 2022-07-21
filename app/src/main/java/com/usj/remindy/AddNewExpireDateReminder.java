package com.usj.remindy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AddNewExpireDateReminder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_expire_date_reminder);

        ImageButton back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              btnCancle(v);
            }
        });

    }

    public void btnCancle(View view) {
        Intent inten = new Intent(AddNewExpireDateReminder.this,ExpiryDateReminder.class);
       startActivity(inten);
    }





}