package com.bidamcat.petjoa.pets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bidamcat.petjoa.R;

public class DogImgActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_img);
    }

    public void clickDogImgMake(View view) {
        Intent intent= new Intent(this, DogImgMakeActivity.class);
        startActivity(intent);
    }
}