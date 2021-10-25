package com.bidamcat.petjoa.itemactivitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.bidamcat.petjoa.R;

public class CatIFMItemActivity extends AppCompatActivity {

    TextView tvTitle;
    TextView tvMsg;
    TextView tvName;
    TextView tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_ifmitem);

        tvTitle= findViewById(R.id.tv_cat_ifm_title_item);
        tvMsg= findViewById(R.id.tv_cat_ifm_msg_item);
        tvName= findViewById(R.id.tv_cat_ifm_name_item);
        tvDate= findViewById(R.id.tv_cat_ifm_date_item);

        Toolbar toolbar= findViewById(R.id.toolbar_catifm_item);
        setSupportActionBar(toolbar);

        ActionBar actionBar= getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);


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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home) onBackPressed();

        return super.onOptionsItemSelected(item);
    }
}