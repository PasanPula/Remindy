package com.usj.remindy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class ExpiryDateReminder extends AppCompatActivity {

    RecyclerView rv;
    LinearLayoutManager lm;
    List<ExpiryItemModel>explist;
    ExpiryItemAdapter adp;

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

        initData();
        initRecyclerView();

    }

    private void initData() {
        explist = new ArrayList<>();
        explist.add(new ExpiryItemModel("Biscuit","Munchee","2022/05/04"));
        explist.add(new ExpiryItemModel("Pepsi","Munchee","2022/05/04"));
        explist.add(new ExpiryItemModel("Apple","Munchee","2022/05/04"));

    }

    private void initRecyclerView() {
        rv = findViewById(R.id.addExRV);
        lm =new LinearLayoutManager(this);
        lm.setOrientation(RecyclerView.VERTICAL);
        rv.setLayoutManager(lm);
        adp = new ExpiryItemAdapter(explist);
        adp.notifyDataSetChanged();
    }


}