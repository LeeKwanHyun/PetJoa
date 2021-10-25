package com.bidamcat.petjoa.itemactivitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bidamcat.petjoa.R;
import com.bumptech.glide.Glide;

public class DogImgItemActivity extends AppCompatActivity {

    ImageView iv;
    TextView tvMsg;
    TextView tvName;
    TextView tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_img_item);

        iv= findViewById(R.id.iv_dog_img_item);
        tvMsg= findViewById(R.id.tv_dog_msg_item);
        tvName= findViewById(R.id.tv_dog_name_item);
        tvDate= findViewById(R.id.tv_dog_date_item);

        Toolbar toolbar= findViewById(R.id.toolbar_dogimg_item);
        setSupportActionBar(toolbar);

        ActionBar actionBar= getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent= getIntent();

        String name= intent.getStringExtra("name");
        String msg= intent.getStringExtra("msg");
        String date= intent.getStringExtra("date");
        String imgUrl= intent.getStringExtra("file");

        tvMsg.setText(msg);
        tvName.setText(name);
        tvDate.setText(date);

        Glide.with(this).load(imgUrl).into(iv);
        //Toast.makeText(this, ""+imgUrl, Toast.LENGTH_SHORT).show();

        iv.setTransitionName("file");

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home) onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}