package com.bidamcat.petjoa.itemactivitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bidamcat.petjoa.R;
import com.bumptech.glide.Glide;

public class CatImgItemActivity extends AppCompatActivity {

    ImageView iv;
    TextView tvMsg;
    TextView tvName;
    TextView tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_img_item);

        iv= findViewById(R.id.iv_cat_img_item);
        tvMsg= findViewById(R.id.tv_cat_msg_item);
        tvName= findViewById(R.id.tv_cat_name_item);
        tvDate= findViewById(R.id.tv_cat_date_item);

        Intent intent= getIntent();

        String name= intent.getStringExtra("name");
        String msg= intent.getStringExtra("msg");
        String date= intent.getStringExtra("date");
        String imgUrl= intent.getStringExtra("file");

        tvMsg.setText(msg);
        tvName.setText(name);
        tvDate.setText(date);

        Glide.with(this).load(imgUrl).into(iv);

        iv.setTransitionName("file");
    }
}