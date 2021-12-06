package com.bidamcat.petjoa.modify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bidamcat.petjoa.R;

public class CatImgModifyActivity extends AppCompatActivity {

    EditText et;
    ImageView iv;

    String imgPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_img_modify);

        et= findViewById(R.id.et_cat_img_modify);
        iv= findViewById(R.id.iv_cat_modify);
    }

    public void click_ImgUpLoad(View view) {
    }

    public void click_Ok(View view) {
    }

    public void click_cancel(View view) {
    }
}