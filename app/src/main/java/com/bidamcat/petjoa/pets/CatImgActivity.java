package com.bidamcat.petjoa.pets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bidamcat.petjoa.R;
import com.bidamcat.petjoa.items.CatImgItem;

import java.util.ArrayList;

public class CatImgActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<CatImgItem> items= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_img);

        recyclerView= findViewById(R.id.cat_recycler);

    }











    public void clickCatImgMake(View view) {
        Intent intent= new Intent(this, CatImgMakeActivity.class);
        startActivity(intent);
    }
}