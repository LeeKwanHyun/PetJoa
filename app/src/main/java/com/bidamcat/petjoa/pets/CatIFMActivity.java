package com.bidamcat.petjoa.pets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bidamcat.petjoa.R;

public class CatIFMActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_ifmactivity);
    }

    public void clickCatIfmMake(View view) {
        Intent intent= new Intent(this, CatIFMMakeActivity.class);
        startActivity(intent);
    }
}