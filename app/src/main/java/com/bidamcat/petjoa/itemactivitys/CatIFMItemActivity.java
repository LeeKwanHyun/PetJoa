package com.bidamcat.petjoa.itemactivitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bidamcat.petjoa.R;
import com.bidamcat.petjoa.pets.RetrofitService2;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class CatIFMItemActivity extends AppCompatActivity {

    TextView tvTitle;
    TextView tvMsg;
    TextView tvName;
    TextView tvDate;

    String no;

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

        no= intent.getStringExtra("no");
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

    public void clickDelete(View view) {
        Retrofit.Builder builder= new Retrofit.Builder();
        builder.baseUrl("http://kimbidam2.dothome.co.kr/");
        builder.addConverterFactory(ScalarsConverterFactory.create());
        Retrofit retrofit= builder.build();

        RetrofitService2 retrofitService2= retrofit.create(RetrofitService2.class);
        Call<String> call= retrofitService2.deleteDateToServer(no);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String s= response.body();
                Toast.makeText(CatIFMItemActivity.this, ""+s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(CatIFMItemActivity.this, "서버오류", Toast.LENGTH_SHORT).show();
            }
        });
        finish();
    }
}