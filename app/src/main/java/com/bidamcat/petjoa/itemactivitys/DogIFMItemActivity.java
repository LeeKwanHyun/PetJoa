package com.bidamcat.petjoa.itemactivitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.bidamcat.petjoa.R;

public class DogIFMItemActivity extends AppCompatActivity {

    TextView tvTitle;
    TextView tvMsg;
    TextView tvName;
    TextView tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_ifmitem);

        tvTitle= findViewById(R.id.tv_dog_ifm_title_item);
        tvMsg= findViewById(R.id.tv_dog_ifm_msg_item);
        tvName= findViewById(R.id.tv_dog_ifm_name_item);
        tvDate= findViewById(R.id.tv_dog_ifm_date_item);

        Intent intent= getIntent();

        String title= intent.getStringExtra("title");
        String name= intent.getStringExtra("name");
        String msg= intent.getStringExtra("msg");
        String date= intent.getStringExtra("date");

        tvTitle.setText(title);
        tvMsg.setText(msg);
        tvName.setText(name);
        tvDate.setText(date);
    }
}