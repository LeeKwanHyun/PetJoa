package com.bidamcat.petjoa.itemactivitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bidamcat.petjoa.R;
import com.bidamcat.petjoa.pets.RetrofitService_PetMissing;
import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class PetMissingItemActivity extends AppCompatActivity {

    ImageView iv;
    TextView tvMsg;
    TextView tvName;
    TextView tvDate;

    String no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_missing_item);

        iv= findViewById(R.id.iv_pet_img_item);
        tvMsg= findViewById(R.id.tv_pet_msg_item);
        tvName= findViewById(R.id.tv_pet_name_item);
        tvDate= findViewById(R.id.tv_pet_date_item);

        Intent intent= getIntent();

        no= intent.getStringExtra("no");
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

    public void clickDelete(View view) {
        Retrofit.Builder builder= new Retrofit.Builder();
        builder.baseUrl("http://kimbidam2.dothome.co.kr/");
        builder.addConverterFactory(ScalarsConverterFactory.create());
        Retrofit retrofit= builder.build();

        RetrofitService_PetMissing retrofitService_petMissing= retrofit.create(RetrofitService_PetMissing.class);
        Call<String> call= retrofitService_petMissing.deleteDateToServer(no);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String s= response.body();
                Toast.makeText(PetMissingItemActivity.this, ""+s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(PetMissingItemActivity.this, "서버오류", Toast.LENGTH_SHORT).show();
            }
        });
        finish();
    }
}